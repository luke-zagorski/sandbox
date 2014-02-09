package com.performgroup.interview.domain;


import com.performgroup.interview.commons.DomainObject;
import com.performgroup.interview.dao.VideoDAO;
import com.performgroup.interview.dao.domain.VideoEntity;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Category Domain Class.
 *          All business logic related directly to its instance goes here.
 */
public interface Video extends DomainObject<VideoEntity, VideoDAO>{

}
