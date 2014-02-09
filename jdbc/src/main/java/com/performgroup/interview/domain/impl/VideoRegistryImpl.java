package com.performgroup.interview.domain.impl;

import com.performgroup.interview.dao.VideoDAO;
import com.performgroup.interview.dao.VideoInCategoryDAO;
import com.performgroup.interview.dao.VideoReportingDAO;
import com.performgroup.interview.dao.domain.VideoEntity;
import com.performgroup.interview.dao.domain.VideoReportingBean;
import com.performgroup.interview.dao.domain.VideoType;
import com.performgroup.interview.domain.Category;
import com.performgroup.interview.domain.Video;
import com.performgroup.interview.domain.VideoRegistry;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author : luke
 * @version : 1.0
 */
public class VideoRegistryImpl implements VideoRegistry {

    private VideoDAO videoDAO;
    private VideoInCategoryDAO videoInCategoryDAO;
    private VideoReportingDAO videoReportingDAO;

    public VideoImpl createVideo(String title, String videoPath, VideoType videoType, Set<Category> videoCategories) {

        VideoEntity videoEntity = videoDAO.createVideo(title, videoPath, videoType);

        videoInCategoryDAO.removeAllVideoInCategoryForVideo(videoEntity);

        for (Category videoCategory : videoCategories) {
            videoInCategoryDAO.createVideoInCategory(videoEntity, videoCategory.getId());
        }

        return getVideo(videoEntity);
    }

    public Video findById(Long videoId) {

        return getVideo(videoDAO.findById(videoId));
    }

    public List<Video> findAllVideos() {

        List<Video> videoList = new ArrayList<Video>();

        for (VideoEntity videoEntity : videoDAO.findAll()) {
            videoList.add(getVideo(videoEntity));
        }

        return videoList;
    }

    public void removeVideo(String title) {

        videoDAO.delete(title);
    }

    /**
     * Reporting Videos count by Type
     *
     * @return Collection of type and count pairs
     */
    public Collection<VideoReportingBean> getVideoTypesSummary() {

        return videoReportingDAO.countByVideoType();
    }

    public Collection<VideoReportingBean> getVideoDaysSummary() {
        return videoReportingDAO.countByDay();

    }

    public VideoReportingBean getVideoTypeSummary(VideoType videoType) {
        return videoReportingDAO.countForVideoType(videoType);
    }

    private VideoImpl getVideo(VideoEntity videoEntity) {
        return new VideoImpl(videoEntity.getId(), videoDAO);
    }

    @Resource
    public void setVideoDAO(VideoDAO videoDAO) {
        this.videoDAO = videoDAO;
    }

    public VideoInCategoryDAO getVideoInCategoryDAO() {
        return videoInCategoryDAO;
    }

    @Resource
    public void setVideoInCategoryDAO(VideoInCategoryDAO videoInCategoryDAO) {
        this.videoInCategoryDAO = videoInCategoryDAO;
    }

    public VideoReportingDAO getVideoReportingDAO() {
        return videoReportingDAO;
    }

    @Resource
    public void setVideoReportingDAO(VideoReportingDAO videoReportingDAO) {
        this.videoReportingDAO = videoReportingDAO;
    }
}
