package pl.com.zagorski.msg.data;

import pl.com.zagorski.msg.data.eao.ContactEao;
import pl.com.zagorski.msg.data.eao.MessageEao;
import pl.com.zagorski.msg.data.eao.UserEao;
import pl.com.zagorski.msg.data.enums.ContactStatusEnum;
import pl.com.zagorski.msg.domain.Contact;
import pl.com.zagorski.msg.domain.Message;
import pl.com.zagorski.msg.domain.User;
import pl.com.zagorski.msg.util.MyLog;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;


/**
 * User: luke
 */
@Stateless
public class MessageCreatorBasicImpl implements MessageCreator {

    @MyLog
    @Inject
    Logger logger;

    @Inject
    MessageEao messageEao;

    @Inject
    UserEao userEao;

    @Inject
    ContactEao contactEao;

    @Override
    public boolean addMessage(String fromUsername, String toUsername, String messageTxt) {

        Message message = new Message(messageTxt);

        StringBuilder sb = new StringBuilder();
        sb.append("[MESSAGE] Adding message: [").append(message).append("] from User:[").append(fromUsername).append("] to User: [").append(toUsername).append("]");
        logger.info(sb.toString());

        // Check if User is blocked in destination User contacts
        Contact contact = contactEao.find(toUsername, fromUsername);
        if (contact != null && ContactStatusEnum.BLOCKED.name().equals(contact.getStatus())) {

            sb.setLength(0);
            sb.append("[MESSAGE] Message blocked");
            logger.info(sb.toString());
            return false;
        }


        User dbFromUser = userEao.find(fromUsername);
        User dbToUser = userEao.find(toUsername);

        dbFromUser.addOutgoingMessage(message);
        dbToUser.addIncomingMessage(message);

        messageEao.save(message);
        return true;
    }

}
