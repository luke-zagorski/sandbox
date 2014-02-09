package pl.com.zagorski.msg.data.eao;

import pl.com.zagorski.msg.util.EMResource;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * User: luke
 */
public abstract class AbstractEao {

    @Inject
    @EMResource
    EntityManager entityManager;

}
