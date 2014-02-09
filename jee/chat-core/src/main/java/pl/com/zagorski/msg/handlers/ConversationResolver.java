package pl.com.zagorski.msg.handlers;

import pl.com.zagorski.msg.bean.ConversationBean;
import pl.com.zagorski.msg.bean.UserBean;
import pl.com.zagorski.msg.data.enums.ContactStatusEnum;
import pl.com.zagorski.msg.handlers.events.ConversationUpdateEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * User: luke
 */
@Model
@SessionScoped
public class ConversationResolver implements Serializable {

    @Inject
    private Logger logger;
    @Inject
    private UserBean currentUser;
    @Inject
    private Event<ConversationUpdateEvent> conversationEventSrv;

    private ConversationBean currentConversation;

    @Produces
    @Named
    public ConversationBean getCurrentConversation() {
        return currentConversation;
    }

    public void onSelectedUserChanged(@Observes(notifyObserver = Reception.ALWAYS) final UserBean selectedUser) {

        currentConversation = new ConversationBean(selectedUser.getNickname());
        currentConversation.setUsername(currentUser.getNickname());

        if (ContactStatusEnum.INCOMING_CHAT.ordinal() == selectedUser.getStatus()) {
            currentConversation.setFirstRead(true);
        } else {
            currentConversation.setInitial(true);
        }

        conversationEventSrv.fire(new ConversationUpdateEvent(currentConversation));
    }


    @PostConstruct
    void initConversation() {
        currentConversation = new ConversationBean();
    }


}
