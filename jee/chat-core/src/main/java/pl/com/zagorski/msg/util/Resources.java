package pl.com.zagorski.msg.util;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 * User : luke
 */
@Stateless
public class Resources {

    @PersistenceContext
    EntityManager em;

    @Produces
    @EMResource
    public EntityManager retrieveEntityManager() {
        return em;
    }

    @Produces
    @MyLog
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
