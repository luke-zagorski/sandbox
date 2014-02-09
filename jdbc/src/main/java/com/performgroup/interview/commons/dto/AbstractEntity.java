package com.performgroup.interview.commons.dto;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Abstract class for all entities
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Transient
    public static final long serialVersionUID = 196919661993L;

}
