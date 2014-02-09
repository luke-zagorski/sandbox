package com.performgroup.interview.domain;

import com.performgroup.interview.dao.domain.VideoReportingBean;
import com.performgroup.interview.dao.domain.VideoType;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Registry pattern implementation for <code>Video</code> domain class related classes
 */
public interface VideoRegistry {

    Video createVideo(String title, String videoPath, VideoType videoType, Set<Category> videoCategory);

    Video findById(Long videoId);

    List<Video> findAllVideos();

    void removeVideo(String title);

    Collection<VideoReportingBean> getVideoTypesSummary();

    Collection<VideoReportingBean> getVideoDaysSummary();

    VideoReportingBean getVideoTypeSummary(VideoType videoType);
}
