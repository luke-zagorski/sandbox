package com.performgroup.interview.dao.domain;

import com.performgroup.interview.commons.dto.AbstractEntity;

import javax.persistence.*;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Video to Category relation
 */
@SequenceGenerator(
        name = "VIDINCAT_SEQ_GEN",
        sequenceName = "VIDINCATSEQ",
        allocationSize = 1
)
@Table
@Entity(name = "VIDEO_IN_CATEGORY")
public class VideoInCategoryEntity extends AbstractEntity {

    private Long Id;
    private VideoEntity toVideo;
    private CategoryEntity toCategory;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIDINCAT_SEQ_GEN")
    @Column(name = "id")
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @ManyToOne
    public VideoEntity getToVideo() {
        return toVideo;
    }

    public void setToVideo(VideoEntity toVideo) {
        this.toVideo = toVideo;
    }

    @ManyToOne
    public CategoryEntity getToCategory() {
        return toCategory;
    }

    public void setToCategory(CategoryEntity toCategory) {
        this.toCategory = toCategory;
    }
}
