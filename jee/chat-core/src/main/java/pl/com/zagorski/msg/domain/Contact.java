package pl.com.zagorski.msg.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * User: luke
 */
@Entity
public class Contact extends BaseEntity {

    private String status;

    @ManyToOne(optional = false)
    private User ownUser;
    @ManyToOne(optional = false)
    private User isUser;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getOwnUser() {
        return ownUser;
    }

    public void setOwnUser(User ownUser) {


        if (this.ownUser != null) {
            this.ownUser.internalRemoveFromContacts(this);
        }

        this.ownUser = ownUser;

        if (ownUser != null) {
            ownUser.internalAddToContacts(this);
        }
    }


    public User getIsUser() {
        return isUser;
    }

    public void setIsUser(User isUser) {

        if (this.isUser != null) {
            this.isUser.internalRemoveFromInContacts(this);
        }

        this.isUser = isUser;

        if (isUser != null) {
            isUser.internalAddToInContacts(this);
        }
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Contact");
        sb.append("{status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
