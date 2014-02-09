package com.performgroup.interview.service.impl;

import com.performgroup.interview.dao.domain.VideoReportingBean;
import com.performgroup.interview.dao.domain.VideoType;
import com.performgroup.interview.domain.Category;
import com.performgroup.interview.domain.CategoryRegistry;
import com.performgroup.interview.domain.Video;
import com.performgroup.interview.domain.VideoRegistry;
import com.performgroup.interview.domain.impl.VideoCategoryImpl;
import com.performgroup.interview.dto.VideoDto;
import com.performgroup.interview.dto.VideoReportSummaryDto;
import com.performgroup.interview.dto.VideoReportingBeanDto;
import com.performgroup.interview.dto.converters.VideoConverter;
import com.performgroup.interview.service.VideoService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

public class VideoServiceImpl implements VideoService {

    private static final Logger LOGGER = Logger.getLogger(VideoServiceImpl.class);

    private VideoRegistry videoRegistry;
    private CategoryRegistry categoryRegistry;

    @Transactional
    public void addVideo(VideoDto videoDto) {

        Set<Category> videoCategories = new HashSet<Category>();

        for (String categoryName : videoDto.getKeywordSet()) {
            videoCategories.add(categoryRegistry.findOrCreateCategory(categoryName));
        }

        videoRegistry.createVideo(videoDto.getTitle(), videoDto.getVideoPath(), videoDto.getVideoType(), videoCategories);
    }

    public void deleteVideo(String videoTitle) {

        videoRegistry.removeVideo(videoTitle);
    }

    public VideoReportSummaryDto getVideoSummaryReport() {

        return new VideoReportSummaryDto(videoRegistry.getVideoTypesSummary(), videoRegistry.getVideoDaysSummary());
    }

    public VideoReportingBeanDto getVideoReport(String videoType) {

        VideoType videoTypeEnum = null;
        try {
            videoTypeEnum = VideoType.valueOf(videoType);
        } catch (IllegalArgumentException e) {
            LOGGER.warn(e);
        }

        VideoReportingBeanDto videoReportingBeanDto = new VideoReportingBeanDto();
        // When video type name does not exist return count 0 for its name and print warning
        if (videoTypeEnum == null) {

            videoReportingBeanDto.setCount(0);
            videoReportingBeanDto.setDescription(videoType);
            return videoReportingBeanDto;
        }
        VideoReportingBean videoReportingBean = videoRegistry.getVideoTypeSummary(videoTypeEnum);
        videoReportingBeanDto.setCount(videoReportingBean.getCount());
        videoReportingBeanDto.setDescription(videoReportingBean.getDescription());

        return videoReportingBeanDto;
    }

    @Transactional
    public VideoDto getVideo(Long videoId) {

        return VideoConverter.getVideoDto(videoRegistry.findById(videoId).getEntity());
    }

    @Transactional
    public Collection<VideoDto> listVideos() {

        Collection<Video> videos = videoRegistry.findAllVideos();
        Collection<VideoDto> videoDtoList = new ArrayList<VideoDto>();

        for (Video video : videos) {

            VideoDto videoDto = VideoConverter.getVideoDto(video.getEntity());
            List<String> keyWords = new ArrayList<String>();

            for (VideoCategoryImpl videoCategory : categoryRegistry.findCategoriesForVideo(video)) {
                keyWords.add(videoCategory.getEntity().getName());
            }

            videoDto.setKeywordSet(keyWords.toArray(new String[keyWords.size()]));
            videoDtoList.add(videoDto);
        }
        return videoDtoList;
    }


    public VideoRegistry getVideoRegistry() {
        return videoRegistry;
    }

    @Resource
    public void setVideoRegistry(VideoRegistry videoRegistry) {
        this.videoRegistry = videoRegistry;
    }


    public CategoryRegistry getCategoryRegistry() {
        return categoryRegistry;
    }

    @Resource
    public void setCategoryRegistry(CategoryRegistry categoryRegistry) {
        this.categoryRegistry = categoryRegistry;
    }

}
