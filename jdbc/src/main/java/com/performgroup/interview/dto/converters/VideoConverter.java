package com.performgroup.interview.dto.converters;

import com.performgroup.interview.dao.domain.VideoEntity;
import com.performgroup.interview.dto.VideoDto;

/**
 * User: luke
 */
public class VideoConverter {

    public static VideoEntity getVideo(VideoDto dto) {

        return new VideoEntity(dto.getTitle(), dto.getVideoPath(), dto.getVideoType());
    }

    public static VideoDto getVideoDto(VideoEntity videoEntity) {

        return new VideoDto(videoEntity.getId(), videoEntity.getCreationDate(), videoEntity.getTitle(), videoEntity.getVideoPath(), videoEntity.getVideoType());
    }

}
