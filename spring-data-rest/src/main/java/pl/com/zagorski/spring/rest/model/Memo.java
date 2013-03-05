package pl.com.zagorski.spring.rest.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: luke
 * Date: 27.01.13
 * Time: 14:47
 */
@Entity

public class Memo {

    @Id
    @GeneratedValue
    private Long id;
    private String note;
    @OneToMany
    private Set<MemoTag> memoTags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<MemoTag> getMemoTags() {
        return memoTags;
    }

    public void setMemoTags(Set<MemoTag> mamoTags) {
        this.memoTags = mamoTags;
    }

}
