package com.performgroup.interview.dao.domain;

import com.performgroup.interview.commons.dto.AbstractEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Video Category Entity
 */
@NamedQueries({
        @NamedQuery(name = "CategoryEntity.findByName", query = "SELECT c FROM CategoryEntity c where c.name=:NAME"),
        @NamedQuery(name = "CategoryEntity.findByVideo", query = "SELECT c FROM CategoryEntity c join c.attachedTo att where att.toVideo=:VID")
})
@SequenceGenerator(
        name = "CAT_SEQ_GEN",
        sequenceName = "CATSEQ",
        allocationSize = 1
)
@Table(name = "CATEGORY")
@Entity
public class CategoryEntity extends AbstractEntity {

    private Long Id;
    private String name; // VideoCategory name

    @OneToMany(mappedBy = "toCategory", fetch = FetchType.LAZY)
    private List<VideoInCategoryEntity> attachedTo;


    public CategoryEntity() {
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAT_SEQ_GEN")
    @Column(name = "id")
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @OneToMany(mappedBy = "toCategory", fetch = FetchType.LAZY)
    public List<VideoInCategoryEntity> getAttachedTo() {
        return attachedTo;
    }

    public void setAttachedTo(List<VideoInCategoryEntity> attachedTo) {
        this.attachedTo = attachedTo;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
