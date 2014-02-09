package com.performgroup.interview.dao.impl;


import com.performgroup.interview.dao.VideoReportingDAO;
import com.performgroup.interview.dao.domain.VideoReportingBean;
import com.performgroup.interview.dao.domain.VideoType;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * The JDBC implementation of the reporting DAO.
 * Needs to remain JDBC driven for the purpose of this task (as opposed to Hibernate).
 */
public class VideoReportingJDBCDAO implements VideoReportingDAO {

    //There are better ways for grouping by date...
    public static final String COUNT_BY_DAY_SQL = "SELECT TO_CHAR(CREATION_DATE,'DD/MM/YYYY') AS CR_DATE, count(*) AS ILE from VIDEO GROUP BY TO_CHAR(CREATION_DATE,'DD/MM/YYYY')";
    public static final String COUNT_BY_TYPE_SQL = "SELECT VIDEO_TYPE, count(*) AS ILE FROM VIDEO GROUP BY VIDEO_TYPE";
    public static final String COUNT_FOR_VIDEO_SQL = "SELECT VIDEO_TYPE, count(*) AS ILE FROM VIDEO WHERE VIDEO_TYPE = ? GROUP BY VIDEO_TYPE";
    public static final String ILE = "ILE";
    public static final String VIDEO_TYPE = "VIDEO_TYPE";
    public static final String CR_DATE = "CR_DATE";

    private SimpleJdbcTemplate simpleJdbcTemplate;

    public SimpleJdbcTemplate getSimpleJdbcTemplate() {
        return simpleJdbcTemplate;
    }

    @Resource
    public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
    }

    public Collection<VideoReportingBean> countByDay() {

        return this.simpleJdbcTemplate.query(COUNT_BY_DAY_SQL, dateMapper);
    }

    public Collection<VideoReportingBean> countByVideoType() {

        return this.simpleJdbcTemplate.query(COUNT_BY_TYPE_SQL, typeMapper);
    }

    public VideoReportingBean countForVideoType(VideoType videoType) {

        return this.simpleJdbcTemplate.queryForObject(COUNT_FOR_VIDEO_SQL, typeMapper, videoType.name());
    }

    //MAPPERS
    private final ParameterizedRowMapper<VideoReportingBean> typeMapper = new ParameterizedRowMapper<VideoReportingBean>() {

        public VideoReportingBean mapRow(ResultSet rs, int rowNum) throws SQLException {

            VideoReportingBean videoReportingBean = new VideoReportingBean();
            videoReportingBean.setCount(rs.getInt(ILE));
            videoReportingBean.setDescription(rs.getString(VIDEO_TYPE));

            return videoReportingBean;
        }
    };

    private final ParameterizedRowMapper<VideoReportingBean> dateMapper = new ParameterizedRowMapper<VideoReportingBean>() {

        public VideoReportingBean mapRow(ResultSet rs, int rowNum) throws SQLException {

            VideoReportingBean videoReportingBean = new VideoReportingBean();
            videoReportingBean.setCount(rs.getInt(ILE));
            videoReportingBean.setDescription(rs.getString(CR_DATE));

            return videoReportingBean;
        }
    };
}
