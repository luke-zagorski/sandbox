package pl.com.zagorski.msg.handlers.events;

import pl.com.zagorski.msg.bean.UserBean;

import java.io.Serializable;

/**
 * User: luke
 */
public class LoggedInEvent implements Serializable {

    private UserBean userBean;

    public LoggedInEvent() {
    }

    public LoggedInEvent(UserBean user) {
        this.userBean = user;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
