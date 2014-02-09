package pl.com.zagorski.msg.bean;

import java.io.Serializable;

/**
 * User: luke
 */
@Dto
public class ConversationBean implements Serializable {

    private String username;
    private String withUser;
    private boolean initial;
    private boolean firstRead;
    private boolean fullHistory;


    public ConversationBean() {
    }

    public ConversationBean(String withUser) {
        this.withUser = withUser;
    }

    public String getWithUser() {
        return withUser;
    }

    public void setWithUser(String withUser) {
        this.withUser = withUser;
    }

    public boolean isInitial() {
        return initial;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public boolean isFirstRead() {
        return firstRead;
    }

    public void setFirstRead(boolean firstRead) {
        this.firstRead = firstRead;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFullHistory() {
        return fullHistory;
    }

    public void setFullHistory(boolean fullHistory) {
        this.fullHistory = fullHistory;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("ConversationBean");
        sb.append("{username='").append(username).append('\'');
        sb.append(", withUser='").append(withUser).append('\'');
        sb.append(", initial=").append(initial);
        sb.append(", firstRead=").append(firstRead);
        sb.append('}');
        return sb.toString();
    }
}
