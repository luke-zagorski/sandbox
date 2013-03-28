package pl.com.zagorski.spring.rest.web.model;

import java.util.List;

/**
 * @author : luke.zagorski
 */
public class Tag {

    private List<Links> links;
    private String name;

    public List<Links> getLinks() {
        return links;
    }

    public void setLinks(List<Links> links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

