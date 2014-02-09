package com.performgroup.interview.dao;

import com.performgroup.interview.dao.domain.VideoEntity;
import com.performgroup.interview.dao.domain.VideoInCategoryEntity;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Data Access Object to access CategoryEntity information
 */
public interface VideoInCategoryDAO extends AbstractDAO<VideoInCategoryEntity> {

    /**
     * Crates new or updates <code>VideoInCategoryEntity<code/> instance combining Videos and Categorise
     *
     * @param videoEntity - <code>VideoEntity<code/> instance
     * @param categoryId  - category identifier
     */
    void createVideoInCategory(VideoEntity videoEntity, Long categoryId);

    /**
     * Removes all VideoCategory relations for particular videoEntity
     *
     * @param videoEntity - <code>VideoEntity</code> instance
     */
    void removeAllVideoInCategoryForVideo(VideoEntity videoEntity);

    /**
     * Persists a <code>VideoInCategoryEntity</code> object into the system
     *
     * @param categoryEntity the <code>VideoInCategoryEntity</code> object to persist
     */
    void save(VideoInCategoryEntity categoryEntity);
}
