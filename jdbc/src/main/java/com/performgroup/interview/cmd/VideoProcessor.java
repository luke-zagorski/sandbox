package com.performgroup.interview.cmd;

import com.performgroup.interview.dao.domain.VideoReportingBean;
import com.performgroup.interview.dto.VideoDto;
import com.performgroup.interview.dto.VideoReportSummaryDto;
import com.performgroup.interview.dto.VideoReportingBeanDto;
import com.performgroup.interview.dto.mappers.VideoMapper;
import com.performgroup.interview.service.VideoService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class VideoProcessor {

    private transient VideoService videoService;
    private transient VideoMapper videoMapper;
    private transient String videoIngestFolder;


    /**
     * Outputs videoDto details to the specified logger
     *
     * @param logger - user console logger
     */
    public void listVideos(Logger logger) {

        Collection<VideoDto> videoDtoList = videoService.listVideos();

        for (VideoDto videoDto : videoDtoList) {
            String videoData = String.format("[%d] - %s / %s - %s \nin Categories (key words): %s", videoDto.getId(), videoDto.getTitle(), videoDto.getVideoType(), videoDto.getVideoPath(), videoDto.getStringKeyWords());
            logger.info(videoData);
        }
    }

    /**
     * Processes a videoDto by ingesting data from XML
     *
     * @param logger    - user console logger
     * @param videoFile - video file path
     */
    public void ingestVideo(Logger logger, String videoFile) {

        final String path = videoIngestFolder + videoFile;

        InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
        if (in == null) {
            logger.info("Cannot find file");
        } else {
            try {
                videoMapper.unmarshallXml(in);
            } catch (IOException e) {
                logger.info("Cannot parse file");
            }
            videoService.addVideo(videoMapper.getDto());
        }
    }

    /**
     * Prints Video summary report
     *
     * @param logger - user console logger
     */
    public void reportSummaryVideo(Logger logger) {

        VideoReportSummaryDto videoReportSummaryDto = videoService.getVideoSummaryReport();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\nCategories:\n");
        for (VideoReportingBean videoReportingBean : videoReportSummaryDto.getTypeReport()) {
            stringBuilder.append("\"").append(videoReportingBean.getDescription()).append("\": ").append(videoReportingBean.getCount()).append("\n");
        }
        stringBuilder.append("\nDays:\n");
        for (VideoReportingBean videoReportingBean : videoReportSummaryDto.getDayReport()) {
            stringBuilder.append(videoReportingBean.getDescription()).append(": ").append(videoReportingBean.getCount()).append("\n");
        }

        logger.info(stringBuilder.toString());
    }

    /**
     * Prints Video report for chosen video type
     *
     * @param logger    - user console logger
     * @param videoType - video type name
     */
    public void reportVideoType(Logger logger, String videoType) {

        VideoReportingBeanDto videoReportingBeanDto = videoService.getVideoReport(videoType);

        logger.info("Video type: \"" + videoReportingBeanDto.getDescription() + "\" count: " + videoReportingBeanDto.getCount());
    }

    public VideoMapper getVideoMapper() {
        return videoMapper;
    }

    @Resource
    public void setVideoMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Resource
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    public void setVideoIngestFolder(String videoIngestFolder) {
        this.videoIngestFolder = videoIngestFolder;
    }
}
