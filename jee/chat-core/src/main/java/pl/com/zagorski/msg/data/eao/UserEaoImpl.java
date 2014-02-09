package pl.com.zagorski.msg.data.eao;

import pl.com.zagorski.msg.domain.User;
import pl.com.zagorski.msg.domain.User_;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * User: luke
 */
@RequestScoped
public class UserEaoImpl extends AbstractEao implements UserEao, Serializable {


    @Override
    public void save(User user) {

        entityManager.persist(user);
    }

    @Override
    public User find(String nickname) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);

        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(User_.nickname), nickname));

        List<User> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        switch (resultList.size()) {
            case 0:
                return null;
            case 1:
                return resultList.get(0);
            default:
                throw new RuntimeException("User table has more than one record with nickname: " + nickname);
        }
    }

    @Override
    public List<User> getUsers() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> messageCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> user = messageCriteriaQuery.from(User.class);
        messageCriteriaQuery.select(user).orderBy(criteriaBuilder.desc(user.get(User_.createdAt)));

        Query query = entityManager.createQuery(messageCriteriaQuery);
        return query.getResultList();
    }


}
