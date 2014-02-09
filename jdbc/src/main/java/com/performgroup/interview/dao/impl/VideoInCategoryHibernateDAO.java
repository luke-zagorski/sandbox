package com.performgroup.interview.dao.impl;

import com.performgroup.interview.dao.VideoInCategoryDAO;
import com.performgroup.interview.dao.domain.CategoryEntity;
import com.performgroup.interview.dao.domain.VideoEntity;
import com.performgroup.interview.dao.domain.VideoInCategoryEntity;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author : luke
 * @version : 1.0
 */
public class VideoInCategoryHibernateDAO extends HibernateDaoSupport implements VideoInCategoryDAO {


    public void createVideoInCategory(VideoEntity videoEntity, Long categoryId) {

        CategoryEntity categoryEntity = (CategoryEntity) getHibernateTemplate().load(CategoryEntity.class, categoryId);

        VideoInCategoryEntity videoInCategoryEntity = new VideoInCategoryEntity();
        videoInCategoryEntity.setToCategory(categoryEntity);
        videoInCategoryEntity.setToVideo(videoEntity);

        save(videoInCategoryEntity);
    }

    public void removeAllVideoInCategoryForVideo(VideoEntity videoEntity) {

        if (videoEntity.getInCategory() != null) {
            for (VideoInCategoryEntity inCategoryEntity : videoEntity.getInCategory()) {
                getHibernateTemplate().delete(inCategoryEntity);
            }
        }
    }

    public void save(VideoInCategoryEntity categoryEntity) {
        getHibernateTemplate().saveOrUpdate(categoryEntity);
    }

    public VideoInCategoryEntity findById(Long id) {
        return (VideoInCategoryEntity) getHibernateTemplate().load(VideoInCategoryEntity.class, id);
    }
}
