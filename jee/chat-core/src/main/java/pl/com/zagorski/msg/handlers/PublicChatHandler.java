package pl.com.zagorski.msg.handlers;

import pl.com.zagorski.msg.bean.MessageBean;
import pl.com.zagorski.msg.bean.UserBean;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.DateFormat;
import java.util.Date;

/**
 * User: luke
 */
@Model
@Stateful
public class PublicChatHandler extends AbstractTopicManager {

    @Inject
    UserBean currentUser;

    private MessageBean newMessage;
    private DateFormat format;

    @Named("publicMessage")
    @Produces
    public MessageBean getNewMessage() {
        return newMessage;
    }

    public void addMessage() {

        logger.info("Creating message:" + newMessage.getText());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(format.format(new Date())).append("] ").append(currentUser.getNickname()).append(" : ").append(newMessage.getText());

        addToTopic(CHAT_TOPIC, "", stringBuilder.toString());
        initNewMessage();
    }

    @PostConstruct
    void initNewMessage() {

        format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        newMessage = new MessageBean();
    }
}
