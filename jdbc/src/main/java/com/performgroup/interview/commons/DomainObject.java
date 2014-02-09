package com.performgroup.interview.commons;

import com.performgroup.interview.commons.dto.AbstractEntity;
import com.performgroup.interview.dao.AbstractDAO;

/**
 * @author : luke
 * @version : 1.0
 */


public interface DomainObject<V extends AbstractEntity, D extends AbstractDAO<V>> {

    Long getId();

    V getEntity();

}

