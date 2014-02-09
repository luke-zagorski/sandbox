package com.performgroup.interview.domain.impl;

import com.performgroup.interview.commons.AbstractEntityDomainObject;
import com.performgroup.interview.dao.CategoryDAO;
import com.performgroup.interview.dao.domain.CategoryEntity;
import com.performgroup.interview.domain.Category;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Category Domain Class.
 *          All business logic related directly to its instance goes here.
 */
public class VideoCategoryImpl extends AbstractEntityDomainObject<CategoryEntity, CategoryDAO> implements Category {


    public VideoCategoryImpl(Long id, CategoryDAO categoryDAO) {
        super(id, categoryDAO);
    }

}
