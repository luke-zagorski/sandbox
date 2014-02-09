package pl.com.zagorski.msg.handlers;

import org.richfaces.application.push.MessageException;
import org.richfaces.application.push.TopicKey;
import org.richfaces.application.push.TopicsContext;
import pl.com.zagorski.msg.util.MyLog;

import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * User: luke
 */

public abstract class AbstractTopicManager {

    @MyLog
    @Inject
    Logger logger;

    @Inject
    TopicModel topicModel;

    public static final String MSG_TOPIC = "privateChatDataTopic";
    public static final String PRIVATE_INFO_TOPIC = "privateChatInfoTopic";
    public static final String CHAT_TOPIC = "publicChatDataTopic";
    public static final String BROADCAST_TOPIC = "broadcastTopic";
    public static final String INFO_TOPIC = "privateInfoTopic";


    void addToTopic(String topic, String subtopic, String message) {

        try {
            TopicsContext topicsContext = topicModel.getTopicsContext();
            TopicKey topicKey;

            if (subtopic == null || subtopic.equals("")) {
                topicKey = new TopicKey(topic);
            } else {
                topicKey = new TopicKey(topic, subtopic);
            }

            topicsContext.getOrCreateTopic(topicKey);
            topicsContext.publish(topicKey, message);

        } catch (MessageException e) {
            logger.warning("Message publish Failure:" + e.getMessage());
        }

    }


}
