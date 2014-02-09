package com.performgroup.interview.commons;

import com.performgroup.interview.commons.dto.AbstractEntity;
import com.performgroup.interview.dao.AbstractDAO;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Abstract Application Domain Object - Entity wrapper
 */
public abstract class AbstractEntityDomainObject<V extends AbstractEntity, D extends AbstractDAO<V>> {

    private final Long id;
    private final D dao;


    protected AbstractEntityDomainObject(Long id, D dao) {
        this.id = id;
        this.dao = dao;
    }

    public Long getId() {
        return id;
    }

    public V getEntity() {
        return dao.findById(getId());
    }
}
