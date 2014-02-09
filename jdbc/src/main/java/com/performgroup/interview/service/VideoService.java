package com.performgroup.interview.service;

import com.performgroup.interview.dto.VideoDto;
import com.performgroup.interview.dto.VideoReportSummaryDto;
import com.performgroup.interview.dto.VideoReportingBeanDto;

import java.util.Collection;

/**
 * A service layer to retrieve Video information
 * <p/>
 * Defines an application's boundary with a layer of services that establishes a set of available operations and coordinates
 * the application's response in each operation.
 */
public interface VideoService {

    /**
     * Find Video instance data by db identifier
     *
     * @param videoId - Video database identifier
     * @return - Video db data wrapped by DTO
     */
    VideoDto getVideo(Long videoId);

    /**
     * Find all Videos in the system
     *
     * @return - Video DTO collection
     */
    Collection<VideoDto> listVideos();

    /**
     * Creates or updates existing video based on video unique title.
     * New data erases old one.
     *
     * @param videoDto - Video DTO object
     */
    void addVideo(VideoDto videoDto);

    /**
     * Remove video data from system based on unique video title
     *
     * @param videoTitle - unique Video title
     */
    void deleteVideo(String videoTitle);

    /**
     * Retrieves Video summary report.
     * Report concerns VideoTypes and Video Creation Dates.
     *
     * @return - <code>VideoReportSummaryDto</code> summary object
     */
    VideoReportSummaryDto getVideoSummaryReport();

    /**
     * Retrieves VideoType report concerns specified type
     *
     * @param videoType - specified type name string
     * @return <code>VideoReportingBeanDto</code> summary object
     */
    VideoReportingBeanDto getVideoReport(String videoType);
}
