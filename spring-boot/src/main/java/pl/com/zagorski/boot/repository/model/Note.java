package pl.com.zagorski.boot.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Luke Zagorski
 *         Date 12.01.2014
 */
@Entity
public class Note {

  @Id
  @GeneratedValue
  private Long id;

  private String text;

  @ManyToOne
  private Cycle cycle;

  public Note() {
  }

  public Note(String text) {
    this.text = text;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Cycle getCycle() {
    return cycle;
  }

  public void setCycle(Cycle cycle) {
    this.cycle = cycle;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Note{");
    sb.append("id=").append(id);
    sb.append(", text='").append(text).append('\'');
    sb.append(", cycle=").append(cycle);
    sb.append('}');
    return sb.toString();
  }
}

