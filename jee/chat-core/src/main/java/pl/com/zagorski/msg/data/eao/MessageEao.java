package pl.com.zagorski.msg.data.eao;

import pl.com.zagorski.msg.domain.Message;

import java.util.List;

/**
 * User: luke
 */
public interface MessageEao {

    void save(Message message);

    List<Message> getLastMessages(int count);

    List<Message> getLastMessages(String fromUsername, String toUsername);

    List<Message> getLastMessages(String fromUsername,String toUsername, int count);
}
