package com.performgroup.interview.dao.domain;

import com.performgroup.interview.commons.dto.AbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * A POJO representing a video in the Perform system.
 */
@NamedQueries({
        @NamedQuery(name = "VideoEntity.findByTitle", query = "SELECT v FROM VideoEntity v where v.title=:TITLE")
})
@SequenceGenerator(
        name = "VID_SEQ_GEN",
        sequenceName = "VIDSEQ",
        allocationSize = 1
)
@Table(name = "VIDEO")
@Entity
public class VideoEntity extends AbstractEntity {

    //Video creation date
    private Date creationDate;
    private Long id;
    //Video title
    private String title;
    //Video file path
    private String videoPath;
    //Video type
    private VideoType videoType;

    @OneToMany(mappedBy = "toVideo", fetch = FetchType.LAZY)
    private List<VideoInCategoryEntity> inCategory;

    public VideoEntity() {

    }

    public VideoEntity(Date creationDate, String title, String videoPath, VideoType videoType) {
        this.creationDate = creationDate;
        this.title = title;
        this.videoPath = videoPath;
        this.videoType = videoType;
    }

    public VideoEntity(String title, String videoPath, VideoType videoType) {
        this.title = title;
        this.videoPath = videoPath;
        this.videoType = videoType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VID_SEQ_GEN")
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title", unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "video_path")
    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Column(name = "video_type")
    @Enumerated(value = EnumType.STRING)
    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }


    @OneToMany(mappedBy = "toVideo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public List<VideoInCategoryEntity> getInCategory() {
        return inCategory;
    }

    public void setInCategory(List<VideoInCategoryEntity> inCategory) {
        this.inCategory = inCategory;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "VideoEntity{" +
                "videoType=" + videoType +
                ", creationDate=" + creationDate +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", videoPath='" + videoPath + '\'' +
                '}';
    }
}
