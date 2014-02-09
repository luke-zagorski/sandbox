package pl.com.zagorski.msg.data;

import pl.com.zagorski.msg.data.eao.ContactEao;
import pl.com.zagorski.msg.data.eao.UserEao;
import pl.com.zagorski.msg.data.enums.ContactStatusEnum;
import pl.com.zagorski.msg.domain.Contact;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * User: luke
 */
@Stateless
public class ContactsProducerBasicImpl implements ContactsProducer, Serializable {

    @Inject
    UserEao userEao;

    @Inject
    ContactEao contactEao;

    @Override
    public List<Contact> getMyActionChatContacts(String username) {

        return contactEao.findWithout(username, ContactStatusEnum.NONE);
    }
}
