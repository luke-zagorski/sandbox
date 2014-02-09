package pl.com.zagorski.msg.handlers;

import pl.com.zagorski.msg.bean.UserBean;
import pl.com.zagorski.msg.data.UserManager;
import pl.com.zagorski.msg.handlers.events.LoggedInEvent;
import pl.com.zagorski.msg.handlers.events.LoggedOutEvent;
import pl.com.zagorski.msg.handlers.events.StatusChangedEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Logger;

@Model
@SessionScoped
public class UserHandler implements Serializable {

    @Inject
    private Logger log;
    @Inject
    private UserManager userManager;

    private UserBean user;

    @Inject
    private Event<LoggedInEvent> loggedInEventSrv;
    @Inject
    private Event<LoggedOutEvent> loggedOutEventSrv;
    @Inject
    private Event<StatusChangedEvent> changedEventSrv;


    @Produces
    @Named("currentUser")
    public UserBean getUser() {

        return user;
    }

    public void setStatus() {

        userManager.updateStatusMessage(user.getNickname(), user.getStatusMessage());
        changedEventSrv.fire(new StatusChangedEvent(user));
    }

    public String logout() {

        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        loggedOutEventSrv.fire(new LoggedOutEvent(user));
        user = null;

        return "success";
    }

    @PostConstruct
    public void initUser() {

        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = userManager.retrieveUser(principal.getName()); // Find User by j_username.
            }
        }

        loggedInEventSrv.fire(new LoggedInEvent(user));
    }


}
