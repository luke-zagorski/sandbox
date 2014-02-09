package pl.com.zagorski.msg.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: luke
 * <br/>
 * <br/>
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Version
    protected Integer version;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();
    }
}
