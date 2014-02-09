package pl.com.zagorski.msg.data;

/**
 * User: luke
 */
public interface ConversationInitializer {

    void notifyIncoming(String fromUsername, String toUsername);

    void clearIncoming(String username, String fromUser);
}
