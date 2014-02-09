package pl.com.zagorski.msg.handlers;

import pl.com.zagorski.msg.handlers.events.ConversationUpdateEvent;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import java.util.HashMap;
import java.util.Map;

/**
 * User: luke
 */
@Singleton
@ApplicationScoped
public class GlobalChanelResolver {

    Map<String, String> userChanelMap;

    @Produces
    public Map<String, String> getUserChanelMap() {
        return userChanelMap;
    }

    public void onConversationChanged(@Observes(notifyObserver = Reception.ALWAYS) final ConversationUpdateEvent conversationUpdateEvent) {

        if(userChanelMap == null){
            initialize();
        }

        userChanelMap.put(conversationUpdateEvent.getConversationBean().getUsername(), conversationUpdateEvent.getConversationBean().getWithUser());
    }


    @PostConstruct
    void initialize() {
        userChanelMap = new HashMap<String, String>();

    }
}
