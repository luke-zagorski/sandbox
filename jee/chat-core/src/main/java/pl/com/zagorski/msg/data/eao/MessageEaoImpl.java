package pl.com.zagorski.msg.data.eao;

import pl.com.zagorski.msg.domain.Message;
import pl.com.zagorski.msg.domain.Message_;
import pl.com.zagorski.msg.domain.User;
import pl.com.zagorski.msg.domain.User_;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * User: luke
 */
@RequestScoped
public class MessageEaoImpl extends AbstractEao implements MessageEao {

    @Override
    public void save(Message message) {

        entityManager.persist(message);
    }

    @Override
    public List<Message> getLastMessages(int count) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> messageCriteriaQuery = criteriaBuilder.createQuery(Message.class);

        Root<Message> message = messageCriteriaQuery.from(Message.class);
        messageCriteriaQuery.select(message).orderBy(criteriaBuilder.desc(message.get(Message_.createdAt)));

        Query query = entityManager.createQuery(messageCriteriaQuery).setMaxResults(count);
        return query.getResultList();
    }

    @Override
    public List<Message> getLastMessages(String fromUsername, String toUsername, int count) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> messageCriteriaQuery = criteriaBuilder.createQuery(Message.class);

        Root<Message> message = messageCriteriaQuery.from(Message.class);
        Join<Message, User> fromUserJoin = message.join(Message_.fromUser);
        Join<Message, User> toUserJoin = message.join(Message_.toUser);

        messageCriteriaQuery.select(message).orderBy(criteriaBuilder.desc(message.get(Message_.createdAt)));
        messageCriteriaQuery.where(criteriaBuilder.or(
                criteriaBuilder.and(criteriaBuilder.equal(fromUserJoin.get(User_.nickname), fromUsername), criteriaBuilder.equal(toUserJoin.get(User_.nickname), toUsername)),
                criteriaBuilder.and(criteriaBuilder.equal(fromUserJoin.get(User_.nickname), toUsername), criteriaBuilder.equal(toUserJoin.get(User_.nickname), fromUsername))
        ));

        Query query = entityManager.createQuery(messageCriteriaQuery).setMaxResults(count);
        return query.getResultList();
    }

    @Override
    public List<Message> getLastMessages(String fromUsername, String toUsername) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> messageCriteriaQuery = criteriaBuilder.createQuery(Message.class);

        Root<Message> message = messageCriteriaQuery.from(Message.class);
        Join<Message, User> fromUserJoin = message.join(Message_.fromUser);
        Join<Message, User> toUserJoin = message.join(Message_.toUser);

        messageCriteriaQuery.select(message).orderBy(criteriaBuilder.desc(message.get(Message_.createdAt)));
        messageCriteriaQuery.where(criteriaBuilder.or(
                criteriaBuilder.and(criteriaBuilder.equal(fromUserJoin.get(User_.nickname), fromUsername), criteriaBuilder.equal(toUserJoin.get(User_.nickname), toUsername)),
                criteriaBuilder.and(criteriaBuilder.equal(fromUserJoin.get(User_.nickname), toUsername), criteriaBuilder.equal(toUserJoin.get(User_.nickname), fromUsername))
        ));

        Query query = entityManager.createQuery(messageCriteriaQuery);
        return query.getResultList();
    }


}
