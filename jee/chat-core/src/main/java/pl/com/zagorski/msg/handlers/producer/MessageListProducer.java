package pl.com.zagorski.msg.handlers.producer;

import pl.com.zagorski.msg.bean.ConversationBean;
import pl.com.zagorski.msg.bean.MessageBean;
import pl.com.zagorski.msg.data.eao.MessageEao;
import pl.com.zagorski.msg.domain.Message;
import pl.com.zagorski.msg.util.properties.ConfiguredBy;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * User: luke
 */
@RequestScoped
public class MessageListProducer {

    public static final String PROPERTIES_MESSAGES = "cfg.properties";
    public static final String PROPERTY_COUNT = "count";

    @Inject
    Logger logger;

    @Inject
    MessageEao messageEao;

    @Inject
    @ConfiguredBy(PROPERTIES_MESSAGES)
    Properties properties;

    @Inject
    ConversationBean currentConversation;

    private List<MessageBean> conversationMessages;

    @Produces
    @Named
    public List<MessageBean> getConversationMessages() {
        return conversationMessages;
    }


    public void onMessageListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final MessageBean message) {

        initMessages();
    }


    @PostConstruct
    void initMessages() {

        if (currentConversation.isFullHistory()) {
            retrieveAllConversationMessages();
            currentConversation.setFullHistory(false);
        } else {
            retrieveAllConversationMessages(Integer.parseInt(properties.getProperty(PROPERTY_COUNT)));
        }
    }


    public void retrieveAllConversationMessages(int count) {

        conversationMessages = new ArrayList<MessageBean>();

        List<Message> dbMessages = messageEao.getLastMessages(currentConversation.getUsername(), currentConversation.getWithUser(), count);

        for (Message msg : dbMessages) {
            conversationMessages.add(new MessageBean(msg.getCreatedAt(), msg.getFromUser().getNickname(), msg.getText()));
        }
        Collections.reverse(conversationMessages);
    }

    public void retrieveAllConversationMessages() {

        conversationMessages = new ArrayList<MessageBean>();

        List<Message> dbMessages = messageEao.getLastMessages(currentConversation.getUsername(), currentConversation.getWithUser());

        for (Message msg : dbMessages) {
            conversationMessages.add(new MessageBean(msg.getCreatedAt(), msg.getFromUser().getNickname(), msg.getText()));
        }
        Collections.reverse(conversationMessages);
    }


}
