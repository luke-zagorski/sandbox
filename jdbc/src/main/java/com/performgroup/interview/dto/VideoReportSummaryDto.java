package com.performgroup.interview.dto;

import com.performgroup.interview.commons.dto.DTO;
import com.performgroup.interview.dao.domain.VideoReportingBean;

import java.util.Collection;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Report bean for Video types an Video creation days
 */
public class VideoReportSummaryDto implements DTO {

    // Collection of Video Types count
    private Collection<VideoReportingBean> typeReport;
    // Collection of Videos created in particular Days
    private Collection<VideoReportingBean> dayReport;

    public VideoReportSummaryDto() {
    }

    public VideoReportSummaryDto(Collection<VideoReportingBean> typeReport, Collection<VideoReportingBean> dayReport) {
        this.typeReport = typeReport;
        this.dayReport = dayReport;
    }

    public Collection<VideoReportingBean> getTypeReport() {
        return typeReport;
    }

    public void setTypeReport(Collection<VideoReportingBean> typeReport) {
        this.typeReport = typeReport;
    }

    public Collection<VideoReportingBean> getDayReport() {
        return dayReport;
    }

    public void setDayReport(Collection<VideoReportingBean> dayReport) {
        this.dayReport = dayReport;
    }


    @Override
    public String toString() {
        return "VideoReportSummaryDto{" +
                "typeReport=" + typeReport +
                ", dayReport=" + dayReport +
                '}';
    }
}
