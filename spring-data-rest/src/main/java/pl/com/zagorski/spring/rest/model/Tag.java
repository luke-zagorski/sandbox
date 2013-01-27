package pl.com.zagorski.spring.rest.model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: luke
 * Date: 27.01.13
 * Time: 14:49
 */
@Entity
public class Tag {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
