package com.performgroup.interview.dao.impl;

import com.performgroup.interview.dao.VideoDAO;
import com.performgroup.interview.dao.domain.CategoryEntity;
import com.performgroup.interview.dao.domain.VideoEntity;
import com.performgroup.interview.dao.domain.VideoType;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Date;
import java.util.List;

/**
 * @author : luke
 * @version : 1.0
 */
public class VideoHibernateDAO extends HibernateDaoSupport implements VideoDAO {

    @SuppressWarnings("unchecked")
    public List<VideoEntity> findAll() {
        return getHibernateTemplate().loadAll(VideoEntity.class);
    }

    public VideoEntity findById(Long videoId) {
        return (VideoEntity) getHibernateTemplate().load(VideoEntity.class, videoId);
    }

    public void delete(VideoEntity videoEntity) {
        getHibernateTemplate().delete(videoEntity);
    }

    public void save(VideoEntity videoEntity) {
        getHibernateTemplate().save(videoEntity);
    }


    public VideoEntity createVideo(String title, String videoPath, VideoType videoType) {

        VideoEntity videoEntity = findByTitle(title);

        if (videoEntity == null) {
            videoEntity = new VideoEntity();
            videoEntity.setCreationDate(new Date());
        }
        videoEntity.setTitle(title);
        videoEntity.setVideoPath(videoPath);
        videoEntity.setVideoType(videoType);

        getHibernateTemplate().saveOrUpdate(videoEntity);

        return videoEntity;
    }

    public VideoEntity findByTitle(String title) {
        @SuppressWarnings("unchecked")
        List<VideoEntity> videoEntities = getHibernateTemplate().findByNamedQueryAndNamedParam("VideoEntity.findByTitle", "TITLE", title);

        if (videoEntities.size() == 0) {
            return null;
        } else if (videoEntities.size() != 1) {
            throw new RuntimeException("Too many Video elements with specific title , should by one");
        }

        return videoEntities.get(0);
    }

    public void delete(String title) {

        VideoEntity videoEntity = findByTitle(title);
        delete(videoEntity);
    }
}