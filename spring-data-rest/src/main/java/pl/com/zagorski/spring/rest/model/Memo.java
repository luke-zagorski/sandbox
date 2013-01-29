package pl.com.zagorski.spring.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luke
 * Date: 27.01.13
 * Time: 14:47
 */
@Entity
public class Memo {

  @Id
  private Long id;
  private String note;
  @OneToMany
  private List<Tag> tags;

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

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }
}
