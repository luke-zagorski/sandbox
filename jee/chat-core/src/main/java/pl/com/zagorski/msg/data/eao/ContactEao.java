package pl.com.zagorski.msg.data.eao;

import pl.com.zagorski.msg.data.enums.ContactStatusEnum;
import pl.com.zagorski.msg.domain.Contact;

import java.util.List;

/**
 * User: luke
 */
public interface ContactEao {

    Contact find(String inUsernameContacts, String username);

    void create(Contact contact);

    List<Contact> findWithout(String username, ContactStatusEnum status);

    List<Contact> find(String username, ContactStatusEnum status);
}
