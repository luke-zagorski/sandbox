package pl.com.zagorski.msg.handlers.events;

import pl.com.zagorski.msg.bean.ConversationBean;

import java.io.Serializable;

/**
 * User: luke
 */
public class ConversationUpdateEvent implements Serializable {

    ConversationBean conversationBean;

    public ConversationUpdateEvent() {
    }

    public ConversationUpdateEvent(ConversationBean conversationBean) {
        this.conversationBean = conversationBean;
    }

    public ConversationBean getConversationBean() {
        return conversationBean;
    }

    public void setConversationBean(ConversationBean conversationBean) {
        this.conversationBean = conversationBean;
    }
}
