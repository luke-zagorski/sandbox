package pl.com.zagorski.msg.handlers;

import org.richfaces.application.push.TopicsContext;

import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

/**
 * User: luke
 */
@ApplicationScoped
@Stateful
public class TopicModel implements Serializable{

    private TopicsContext topicsContext;

    public TopicsContext getTopicsContext() {

        if(topicsContext == null){
            topicsContext = TopicsContext.lookup();
        }
        return topicsContext;
    }



}
