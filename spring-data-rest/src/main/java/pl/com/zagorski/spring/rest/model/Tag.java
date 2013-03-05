package pl.com.zagorski.spring.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: luke
 * Date: 27.01.13
 * Time: 14:49
 */
@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany
    private Set<MemoTag> memoTags;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MemoTag> getMemoTags() {
        return memoTags;
    }

    public void setMemoTags(Set<MemoTag> memoTags) {
        this.memoTags = memoTags;
    }
}
