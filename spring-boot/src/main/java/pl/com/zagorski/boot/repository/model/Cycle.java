package pl.com.zagorski.boot.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Luke Zagorski
 *         Date 12.01.2014
 */
@Entity
public class Cycle {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @OneToMany(mappedBy = "cycle")
  private List<Note> notes;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
