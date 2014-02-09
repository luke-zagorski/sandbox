package pl.com.zagorski.msg.data;

import pl.com.zagorski.msg.domain.Contact;

import java.util.List;

/**
 * User: luke
 */
public interface ContactsProducer {

    List<Contact> getMyActionChatContacts(String username);
}
