package pl.com.zagorski.msg.data;

import pl.com.zagorski.msg.data.eao.ContactEao;
import pl.com.zagorski.msg.data.eao.UserEao;
import pl.com.zagorski.msg.data.enums.ContactStatusEnum;
import pl.com.zagorski.msg.domain.Contact;
import pl.com.zagorski.msg.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 * User: Luke
 */
@Stateless
public class ContactMangerBasicImpl implements ContactManager {

    @Inject
    ContactEao contactEao;

    @Inject
    UserEao userEao;

    @Override
    public void blockUser(String nickname, String withUser) {

        Contact contact = contactEao.find(nickname, withUser);

        if (contact == null) {

            contact = new Contact();
            User currentUser = userEao.find(nickname);
            User contactUser = userEao.find(withUser);
            contact.setIsUser(contactUser);
            contact.setOwnUser(currentUser);
            contactEao.create(contact);
        }
        contact.setStatus(ContactStatusEnum.BLOCKED.name());

    }

    @Override
    public void unblockUser(String nickname, String withUser) {

        Contact contact = contactEao.find(nickname, withUser);


        if (ContactStatusEnum.BLOCKED.name().equals(contact.getStatus())) {
            contact.setStatus(ContactStatusEnum.NONE.name());
        }
    }
}
