package com.performgroup.interview.dao;


import com.performgroup.interview.dao.domain.VideoEntity;
import com.performgroup.interview.dao.domain.VideoType;

import java.util.Collection;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Data Access Object to access VideoEntity information
 */
public interface VideoDAO extends AbstractDAO<VideoEntity> {

    /**
     * Find all videos in the system
     *
     * @return a collection of <code>VideoEntity</code>
     */
    Collection<VideoEntity> findAll();

    /**
     * Find one video in the system
     *
     * @param title - title string of <code>VideoEntity</code> object to find
     * @return a single of <code>VideoEntity</code>
     */
    VideoEntity findByTitle(String title);

    /**
     * Creates new <code>VideoEntity</code> instance
     *
     * @param title     - video title name
     * @param videoPath - path to video
     * @param videoType - type of video listed in <code>VideoType<code/>
     * @return created <code>VideoEntity</code>
     */
    VideoEntity createVideo(String title, String videoPath, VideoType videoType);

    /**
     * Persists a <code>VideoEntity</code> object into the system
     *
     * @param videoEntity the <code>VideoEntity</code> object to persist
     */
    void save(VideoEntity videoEntity);

    /**
     * Deletes a <code>VideoEntity</code> object from the system
     *
     * @param videoEntity the <code>VideoEntity</code> object to delete
     */
    void delete(VideoEntity videoEntity);

    /**
     * Deletes a <code>VideoEntity</code> object from the system
     *
     * @param title - title string of <code>VideoEntity</code> object to delete
     */
    void delete(String title);
}