package pl.com.zagorski.msg.data.eao;

import pl.com.zagorski.msg.data.enums.ContactStatusEnum;
import pl.com.zagorski.msg.domain.Contact;
import pl.com.zagorski.msg.domain.Contact_;
import pl.com.zagorski.msg.domain.User;
import pl.com.zagorski.msg.domain.User_;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;


@RequestScoped
public class ContactEaoImpl extends AbstractEao implements ContactEao {

    @Override
    public Contact find(String inUsernameContacts, String username) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> contactCriteriaQuery = criteriaBuilder.createQuery(Contact.class);

        Root<Contact> contact = contactCriteriaQuery.from(Contact.class);
        Join<Contact, User> inUserContactsJoin = contact.join(Contact_.ownUser);
        Join<Contact, User> isInContactsJoin = contact.join(Contact_.isUser);


        contactCriteriaQuery.select(contact).orderBy(criteriaBuilder.desc(contact.get(Contact_.updatedAt)));
        contactCriteriaQuery.where(
                criteriaBuilder.and(criteriaBuilder.equal(inUserContactsJoin.get(User_.nickname), inUsernameContacts), criteriaBuilder.equal(isInContactsJoin.get(User_.nickname), username))
        );

        Query query = entityManager.createQuery(contactCriteriaQuery);
        List<Contact> resultList = query.getResultList();

        switch (resultList.size()) {
            case 0:
                return null;
            case 1:
                return resultList.get(0);
            default:
                throw new RuntimeException("User table has more than one Contact with nickname: " + username);
        }
    }


    @Override
    public List<Contact> find(String username, ContactStatusEnum status) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> contactCriteriaQuery = criteriaBuilder.createQuery(Contact.class);

        Root<Contact> contact = contactCriteriaQuery.from(Contact.class);
        Join<Contact, User> inUserContactsJoin = contact.join(Contact_.ownUser);


        contactCriteriaQuery.select(contact).orderBy(criteriaBuilder.desc(contact.get(Contact_.updatedAt)));
        contactCriteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(inUserContactsJoin.get(User_.nickname), username), criteriaBuilder.equal(contact.get(Contact_.status), status.name())));

        Query query = entityManager.createQuery(contactCriteriaQuery);
        return query.getResultList();
    }


    @Override
    public void create(Contact contact) {
        entityManager.persist(contact);

    }

    @Override
    public List<Contact> findWithout(String username, ContactStatusEnum status) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> contactCriteriaQuery = criteriaBuilder.createQuery(Contact.class);

        Root<Contact> contact = contactCriteriaQuery.from(Contact.class);
        Join<Contact, User> inUserContactsJoin = contact.join(Contact_.ownUser);


        contactCriteriaQuery.select(contact).orderBy(criteriaBuilder.desc(contact.get(Contact_.updatedAt)));
        contactCriteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(inUserContactsJoin.get(User_.nickname), username), criteriaBuilder.notEqual(contact.get(Contact_.status), status.name())));

        Query query = entityManager.createQuery(contactCriteriaQuery);
        return query.getResultList();
    }

}
