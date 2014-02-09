package pl.com.zagorski.msg.util;

import org.richfaces.application.push.Topic;
import org.richfaces.application.push.TopicKey;
import org.richfaces.application.push.TopicsContext;
import org.richfaces.application.push.impl.DefaultMessageDataSerializer;
import pl.com.zagorski.msg.handlers.AbstractTopicManager;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * User: luke
 */
public class TopicInitializer implements SystemEventListener {

    @Inject
    Logger logger;


    public void processEvent(SystemEvent event) throws AbortProcessingException {

        TopicsContext topicsContext = TopicsContext.lookup();

        Topic topic = topicsContext.getOrCreateTopic(new TopicKey(AbstractTopicManager.MSG_TOPIC));
        topic.setMessageDataSerializer(DefaultMessageDataSerializer.instance());

        topic = topicsContext.getOrCreateTopic(new TopicKey(AbstractTopicManager.INFO_TOPIC));
        topic.setMessageDataSerializer(DefaultMessageDataSerializer.instance());

        topic = topicsContext.getOrCreateTopic(new TopicKey(AbstractTopicManager.BROADCAST_TOPIC));
        topic.setMessageDataSerializer(DefaultMessageDataSerializer.instance());

        topic = topicsContext.getOrCreateTopic(new TopicKey(AbstractTopicManager.CHAT_TOPIC));
        topic.setMessageDataSerializer(DefaultMessageDataSerializer.instance());

        topic = topicsContext.getOrCreateTopic(new TopicKey(AbstractTopicManager.PRIVATE_INFO_TOPIC));
        topic.setMessageDataSerializer(DefaultMessageDataSerializer.instance());
    }


    @Override
    public boolean isListenerForSource(Object source) {
        return true;
    }
}
