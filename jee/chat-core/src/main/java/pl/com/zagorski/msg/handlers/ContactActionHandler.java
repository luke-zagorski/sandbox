package pl.com.zagorski.msg.handlers;

import pl.com.zagorski.msg.bean.ConversationBean;
import pl.com.zagorski.msg.data.ContactManager;
import pl.com.zagorski.msg.handlers.events.HistorySizeChangeEvent;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * User: luke
 */
@Model
public class ContactActionHandler {

    @Inject
    Logger logger;

    @Inject
    ContactManager contactManager;

    @Inject
    ConversationBean conversationBean;

    @Inject
    private Event<HistorySizeChangeEvent> historySizeChangeEventSrv;


    public void blockUser() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Block user :[").append(conversationBean.getWithUser()).append("] for current User:[").append(conversationBean.getUsername()).append("]");
        logger.info(stringBuilder.toString());


        contactManager.blockUser(conversationBean.getUsername(), conversationBean.getWithUser());
    }

    public void unblockUser() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Block user :[").append(conversationBean.getWithUser()).append("] for current User:[").append(conversationBean.getUsername()).append("]");
        logger.info(stringBuilder.toString());

        contactManager.unblockUser(conversationBean.getUsername(), conversationBean.getWithUser());
    }

    public void history() {

        conversationBean.setFullHistory(true);
        historySizeChangeEventSrv.fire(new HistorySizeChangeEvent());
    }


}
