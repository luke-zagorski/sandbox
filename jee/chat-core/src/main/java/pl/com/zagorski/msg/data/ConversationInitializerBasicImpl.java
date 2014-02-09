package pl.com.zagorski.msg.data;

import pl.com.zagorski.msg.data.eao.ContactEao;
import pl.com.zagorski.msg.data.eao.UserEao;
import pl.com.zagorski.msg.data.enums.ContactStatusEnum;
import pl.com.zagorski.msg.domain.Contact;
import pl.com.zagorski.msg.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;


/**
 * User: luke
 */
@Stateless
public class ConversationInitializerBasicImpl implements ConversationInitializer {

    @Inject
    Logger logger;

    @Inject
    UserEao userEao;

    @Inject
    ContactEao contactEao;

    @Override
    public void notifyIncoming(String fromUsername, String toUsername) {


        Contact contact = contactEao.find(toUsername, fromUsername);

        if (contact == null) {

            User fromUser = userEao.find(fromUsername);
            User toUser = userEao.find(toUsername);

            contact = new Contact();
            contact.setIsUser(fromUser);
            contact.setOwnUser(toUser);
            contact.setStatus(ContactStatusEnum.INCOMING_CHAT.name());

            contactEao.create(contact);
        } else {
            contact.setStatus(ContactStatusEnum.INCOMING_CHAT.name());
        }
    }

    @Override
    public void clearIncoming(String username, String fromUsername) {


        Contact contact = contactEao.find(username, fromUsername);
        contact.setStatus(ContactStatusEnum.NONE.name());
    }

}
