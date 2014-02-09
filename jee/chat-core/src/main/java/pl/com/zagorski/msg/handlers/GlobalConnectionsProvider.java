package pl.com.zagorski.msg.handlers;

import pl.com.zagorski.msg.bean.UserBean;
import pl.com.zagorski.msg.data.enums.ContactStatusEnum;
import pl.com.zagorski.msg.handlers.events.LoggedInEvent;
import pl.com.zagorski.msg.handlers.events.LoggedOutEvent;
import pl.com.zagorski.msg.handlers.events.StatusChangedEvent;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * User: luke
 */
@Singleton
@ApplicationScoped
public class GlobalConnectionsProvider extends AbstractTopicManager implements Serializable {

    @Inject
    private Logger logger;

    Set<UserBean> userBeanSet;

    @Produces
    public Set<UserBean> getUserBeanSet() {
        return userBeanSet;
    }

    public void onStatusChanged(@Observes(notifyObserver = Reception.ALWAYS) final StatusChangedEvent statusChangedEvent) {

        userBeanSet.remove(statusChangedEvent.getUserBean());
        userBeanSet.add(statusChangedEvent.getUserBean());

        addToTopic(BROADCAST_TOPIC, "", "");
    }

    public void onUserLoggedIn(@Observes(notifyObserver = Reception.ALWAYS) final LoggedInEvent loggedIn) {

        UserBean userBean = loggedIn.getUserBean();
        userBean.setStatus(ContactStatusEnum.ONLINE.ordinal());

        userBeanSet.add(userBean);
        addToTopic(BROADCAST_TOPIC, "", "");
    }

    public void onUserLoggedOut(@Observes(notifyObserver = Reception.ALWAYS) final LoggedOutEvent loggedOutEvent) {

        UserBean userBean = loggedOutEvent.getUserBean();

        userBeanSet.remove(userBean);
        addToTopic(BROADCAST_TOPIC, "", "");
    }


    @PostConstruct
    void init() {
        userBeanSet = new HashSet<UserBean>();
    }


}
