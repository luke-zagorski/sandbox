package pl.com.zagorski.spring.rest.web.model;

import java.util.List;

/**
 * @author : luke.zagorski
 */
public class Response {

    private List<Links> links;
    private List<Tag> content;

    public List<Links> getLinks() {
        return links;
    }

    public void setLinks(List<Links> links) {
        this.links = links;
    }

    public List<Tag> getContent() {
        return content;
    }

    public void setContent(List<Tag> tag) {
        this.content = tag;
    }
}
