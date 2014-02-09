package pl.com.zagorski.msg.handlers;

import pl.com.zagorski.msg.bean.ConversationBean;
import pl.com.zagorski.msg.bean.MessageBean;
import pl.com.zagorski.msg.bean.UserBean;
import pl.com.zagorski.msg.data.ConversationInitializer;
import pl.com.zagorski.msg.data.MessageCreator;
import pl.com.zagorski.msg.handlers.events.ConversationUpdateEvent;
import pl.com.zagorski.msg.handlers.events.HistorySizeChangeEvent;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

/**
 * User: luke
 */
@Model
@Stateful
public class PrivateChatHandler extends AbstractTopicManager {

    private MessageBean newMessage;

    @Inject
    UserBean currentUser;

    @Inject
    ConversationInitializer conversationInitializer;

    @Inject
    private ConversationBean currentConversation;

    @Inject
    Map<String, String> userChanelMap;

    @Inject
    MessageCreator messageCreator;

    private DateFormat format;

    @Produces
    @Named("privateMessage")
    public MessageBean getNewMessage() {
        return newMessage;
    }


    public void onConversationChanged(@Observes(notifyObserver = Reception.ALWAYS) final ConversationUpdateEvent conversationUpdateEvent) {

        StringBuilder stringBuilder = new StringBuilder();

        if (currentConversation.isFirstRead()) {

            stringBuilder.append("First read from :[").append(currentConversation.getWithUser()).append("] current User:[").append(currentUser.getNickname()).append("]");
            logger.info(stringBuilder.toString());

            clearIncomingNotification();
        }
        addToTopic(PRIVATE_INFO_TOPIC, currentUser.getNickname(), "");
    }

    public void onConversationChanged(@Observes(notifyObserver = Reception.ALWAYS) final HistorySizeChangeEvent historySizeChangeEvent) {
        addToTopic(PRIVATE_INFO_TOPIC, currentUser.getNickname(), "");
    }

    public void addMessage() {

        StringBuilder stringBuilder = new StringBuilder();

        if (messageCreator.addMessage(currentUser.getNickname(), currentConversation.getWithUser(), newMessage.getText())) {

            //First message after conversation changed
            if (currentConversation.isInitial()) {

                stringBuilder.append("Initial to :[").append(currentConversation.getWithUser()).append("] current User:[").append(currentUser.getNickname()).append("]");
                logger.info(stringBuilder.toString());

                sendChatNotificationToContact();
            }

            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append("[").append(format.format(new Date())).append("] ").append(currentUser.getNickname()).append(" : ").append(newMessage.getText());

            addToTopic(MSG_TOPIC, currentUser.getNickname(), msgBuilder.toString());
            if (userChanelMap.get(currentConversation.getWithUser()) == null || userChanelMap.get(currentConversation.getWithUser()).equals(currentUser.getNickname())) {

                addToTopic(MSG_TOPIC, currentConversation.getWithUser(), msgBuilder.toString());
            } else {
                sendChatNotificationToContact();
            }

            initNewMessage();
        }
    }

    private void clearIncomingNotification() {

        conversationInitializer.clearIncoming(currentUser.getNickname(), currentConversation.getWithUser());
        addToTopic(INFO_TOPIC, currentUser.getNickname(), "");

        currentConversation.setFirstRead(false);
    }

    private void sendChatNotificationToContact() {

        conversationInitializer.notifyIncoming(currentUser.getNickname(), currentConversation.getWithUser());
        addToTopic(INFO_TOPIC, currentConversation.getWithUser(), "");

        currentConversation.setInitial(false);
    }


    @PostConstruct
    void initNewMessage() {

        newMessage = new MessageBean();
        format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    }
}
