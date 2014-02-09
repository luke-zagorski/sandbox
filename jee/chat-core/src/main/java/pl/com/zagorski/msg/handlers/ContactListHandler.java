package pl.com.zagorski.msg.handlers;

import pl.com.zagorski.msg.bean.UserBean;
import pl.com.zagorski.msg.data.ContactsProducer;
import pl.com.zagorski.msg.data.eao.UserEao;
import pl.com.zagorski.msg.data.enums.ContactStatusEnum;
import pl.com.zagorski.msg.domain.Contact;
import pl.com.zagorski.msg.domain.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

import static java.util.Collections.sort;

/**
 * User: luke
 */
@Model
@SessionScoped
public class ContactListHandler implements Serializable{

    @Inject
    UserEao userEao;
    @Inject
    ContactsProducer contactsProducer;
    @Inject
    Set<UserBean> loggedUsers;
    @Inject
    UserBean myUser;

    @Inject
    private Event<UserBean> selectedUserEventSrv;

    UserBean selectedUser;
    UserDtoModel userDtoModel;


    public UserDtoModel getUserDtoModel() {
        retrieveAllUsers();
        return userDtoModel;
    }

    public void setUserDtoModel(UserDtoModel userDtoModel) {
        this.userDtoModel = userDtoModel;
    }

    @Named("selectedUser")
    public UserBean getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserBean selectedUser) {
        this.selectedUser = selectedUser;
    }


    public void createConversation() {
        selectedUserEventSrv.fire(selectedUser);
    }

    @PostConstruct
    public void retrieveAllUsers() {

        Set<UserBean> allUsersBeanSet = new HashSet<UserBean>();
        List<User> allUsersList = userEao.getUsers();
        for (User user : allUsersList) {
            allUsersBeanSet.add(convert(user));
        }

        Set<UserBean> incomingUsersBeanSet = new HashSet<UserBean>();
        List<Contact> myContacts = contactsProducer.getMyActionChatContacts(myUser.getNickname());
        for (Contact contact : myContacts) {
            incomingUsersBeanSet.add(convert(contact));
        }

        Set<UserBean> myUsers = new HashSet<UserBean>();

        myUsers.addAll(incomingUsersBeanSet);
        myUsers.addAll(loggedUsers);
        myUsers.addAll(allUsersBeanSet);
        myUsers.remove(myUser);

        List<UserBean> myUsersList = new ArrayList<UserBean>(myUsers);

        sort(myUsersList, Collections.reverseOrder());

        userDtoModel = new UserDtoModel(myUsersList);
    }


    UserBean convert(Contact contact) {

        UserBean userBean = new UserBean(contact.getIsUser().getId(), contact.getIsUser().getNickname(), contact.getIsUser().getStatusMessage());
        userBean.setStatus(ContactStatusEnum.valueOf(contact.getStatus()));

        return userBean;
    }

    UserBean convert(User user) {

        UserBean userBean = new UserBean(user.getId(), user.getNickname(), user.getStatusMessage());
        userBean.setStatus(user.getStatus());

        return userBean;
    }


}
