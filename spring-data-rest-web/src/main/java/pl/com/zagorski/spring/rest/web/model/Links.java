package pl.com.zagorski.spring.rest.web.model;

/**
 * @author : luke.zagorski
 */
public class Links {

    private String rel;
    private String href;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
