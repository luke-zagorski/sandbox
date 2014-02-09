package com.performgroup.interview.dao.domain;

/**
 * A bean to contain reporting information.
 * Only contains a number and a string description.
 */
public class VideoReportingBean {

    // Value count
    private Integer count;
    // Value description
    private String description;

    public VideoReportingBean() {
    }

    public VideoReportingBean(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
