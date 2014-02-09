package com.performgroup.interview.domain.impl;

import com.performgroup.interview.commons.AbstractEntityDomainObject;
import com.performgroup.interview.dao.VideoDAO;
import com.performgroup.interview.dao.domain.VideoEntity;
import com.performgroup.interview.domain.Video;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Video Domain Class.
 *          All business logic related directly to its instance goes here.
 */
public class VideoImpl extends AbstractEntityDomainObject<VideoEntity, VideoDAO> implements Video {


    public VideoImpl(Long id, VideoDAO videoDAO) {
        super(id, videoDAO);
    }


}
