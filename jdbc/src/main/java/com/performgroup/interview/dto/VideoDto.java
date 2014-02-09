package com.performgroup.interview.dto;

import com.performgroup.interview.commons.dto.DTO;
import com.performgroup.interview.dao.domain.VideoType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          DTO class for Video data
 */
public class VideoDto implements Serializable, DTO {

    //Database id
    private Long id;
    //Database creation date
    private Date creationDate;
    //Video title
    private String title;
    //Video file path
    private String videoPath;
    //Video type
    private VideoType videoType;
    // Key words also known as Categories
    private String[] keywordSet;

    public VideoDto() {
    }

    public VideoDto(Long id, Date creationDate, String title, String videoPath, VideoType videoType, String... keywordSet) {
        this.id = id;
        this.creationDate = creationDate;
        this.title = title;
        this.videoPath = videoPath;
        this.videoType = videoType;
        this.keywordSet = keywordSet;
    }

    public VideoDto(String title, String videoPath, VideoType videoType, String... keywordSet) {

        this.title = title;
        this.videoPath = videoPath;
        this.videoType = videoType;
        this.keywordSet = keywordSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }

    public String[] getKeywordSet() {
        return keywordSet;
    }

    public void setKeywordSet(String[] keywordSet) {
        this.keywordSet = keywordSet;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStringKeyWords() {

        StringBuilder stringBuffer = new StringBuilder();

        for (String str : getKeywordSet()) {
            stringBuffer.append(str).append(", ");
        }

        if (stringBuffer.length() > 1) {
            stringBuffer = stringBuffer.replace(stringBuffer.length() - 2, stringBuffer.length(), ";");
        }

        return stringBuffer.toString();
    }


    @Override
    public String toString() {
        return "VideoDto{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", title='" + title + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", videoType=" + videoType +
                ", keywordSet=" + (keywordSet == null ? null : Arrays.asList(keywordSet)) +
                '}';
    }
}
