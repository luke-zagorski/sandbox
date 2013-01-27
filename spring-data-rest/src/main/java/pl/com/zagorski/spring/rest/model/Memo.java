package pl.com.zagorski.spring.rest.model;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luke
 * Date: 27.01.13
 * Time: 14:47
 */
@Entity
public class Memo {

    private String note;
    private List<Tag> tags;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
