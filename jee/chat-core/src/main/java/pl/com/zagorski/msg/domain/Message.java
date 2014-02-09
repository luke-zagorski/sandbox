package pl.com.zagorski.msg.domain;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * User: luke
 */
@Entity
@Cacheable
public class Message extends BaseEntity {

    @ManyToOne(optional = false)
    private User fromUser;
    @ManyToOne(optional = false)
    private User toUser;

    private String text;

    public Message() {
    }

    public Message(String messageTxt) {
        this.text = messageTxt;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {

        if (this.fromUser != null) {
            this.fromUser.internalRemoveOutgoingMessage(this);
        }

        this.fromUser = fromUser;

        if (fromUser != null) {
            fromUser.internalAddOutgoingMessage(this);
        }
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {

        if (this.toUser != null) {
            this.toUser.internalRemoveIncomingMessage(this);
        }

        this.toUser = toUser;

        if (toUser != null) {
            toUser.internalAddIncomingMessage(this);
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Message");
        sb.append("{text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
