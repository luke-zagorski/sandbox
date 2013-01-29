package pl.com.zagorski.spring.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: luke
 * Date: 27.01.13
 * Time: 14:49
 */
@Entity
public class Tag {

  @Id
  private Long id;
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
}
