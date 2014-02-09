package pl.com.zagorski.msg.data;

/**
 * User: luke
 */
public interface MessageCreator {

    boolean addMessage(String fromUsername, String toUsername, String message);
}
