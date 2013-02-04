package pl.com.zagorski.spring.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Luke Zagorski
 *         Date 02.02.2013
 */
@Entity
public class MemoTag {

  @Id
  private Long id;
  @ManyToOne(optional = false)
  private Tag tag;
  @ManyToOne(optional = false)
  private Memo memo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  public Memo getMemo() {
    return memo;
  }

  public void setMemo(Memo memo) {
    this.memo = memo;
  }
}
