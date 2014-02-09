package com.performgroup.interview.domain;

import com.performgroup.interview.commons.DomainObject;
import com.performgroup.interview.dao.CategoryDAO;
import com.performgroup.interview.dao.domain.CategoryEntity;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Category Domain Class.
 *          All business logic related directly to its instance goes here.
 */
public interface Category extends DomainObject<CategoryEntity, CategoryDAO>{


}
