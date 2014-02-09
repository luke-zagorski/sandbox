package com.performgroup.interview.dao;

import com.performgroup.interview.commons.dto.AbstractEntity;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          AbstractDAO containing common DAO operations across domain objects
 */
public interface AbstractDAO<E extends AbstractEntity> {

    /**
     * Finds persisted object by db identifier
     *
     * @param id - Entity identifier
     * @return Entity object instance
     */
    E findById(Long id);
}
