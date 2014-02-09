package pl.com.zagorski.msg.handlers.events;

import pl.com.zagorski.msg.bean.UserBean;

import java.io.Serializable;

/**
 * User: luke
 */
public class AbstractUserEvent implements Serializable {

    private UserBean userBean;

    public AbstractUserEvent(UserBean userBean) {
        this.userBean = userBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
