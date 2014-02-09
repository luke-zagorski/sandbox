package pl.com.zagorski.msg.handlers.events;

import pl.com.zagorski.msg.bean.UserBean;

/**
 * User: luke
 */
public class StatusChangedEvent extends AbstractUserEvent{


    public StatusChangedEvent(UserBean userBean) {
        super(userBean);
    }
}
