package pl.com.zagorski.msg.handlers.events;

import pl.com.zagorski.msg.bean.UserBean;

/**
 * User: luke
 */
public class LoggedOutEvent extends AbstractUserEvent {

    public LoggedOutEvent(UserBean userBean) {
        super(userBean);
    }
}
