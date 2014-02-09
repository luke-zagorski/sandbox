package com.performgroup.interview.dao;

import com.performgroup.interview.dao.domain.VideoReportingBean;
import com.performgroup.interview.dao.domain.VideoType;
import com.performgroup.interview.dao.impl.VideoReportingJDBCDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static junit.framework.Assert.assertEquals;

/**
 * User: luke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:testApplicationContext.xml"})
public class VideoReportingJDBCDAOTest {

    @Autowired
    VideoReportingJDBCDAO videoReportingJDBCDAO;

    @Test
    public void countForVideoType() throws Exception {

        // Given - Fixtures in "performdb_test.script"

        // When
        VideoReportingBean videoReportingBean = videoReportingJDBCDAO.countForVideoType(VideoType.FRUIT_MATCH);

        // Then
        assertEquals("Incorrect number of FRUIT_MATCH types", 1, videoReportingBean.getCount().intValue());
    }

    @Test
    @ExpectedException(EmptyResultDataAccessException.class)
    public void countForNonExistingVideoType() throws Exception {

        // Given - Fixtures in "performdb_test.script"

        // When
        videoReportingJDBCDAO.countForVideoType(VideoType.VEGETABLE_MATCH);

        // Then EX
    }

    @Test
    public void testCountByType() throws Exception {

        // Given - Fixtures in "performdb_test.script"

        // When
        Collection<VideoReportingBean> videoReportingBeans = videoReportingJDBCDAO.countByVideoType();

        // Then
        assertEquals("Incorrect number of types", 2, videoReportingBeans.size());
        for (VideoReportingBean videoReportingBean : videoReportingBeans) {
            assertEquals("Incorrect type count", 1, videoReportingBean.getCount().intValue());
        }
    }

    @Test
    public void testCountByDate() throws Exception {

        // Given - Fixtures in "performdb_test.script"

        // When
        Collection<VideoReportingBean> videoReportingBeans = videoReportingJDBCDAO.countByDay();

        // Then
        assertEquals("Incorrect number of days", 2, videoReportingBeans.size());
        for (VideoReportingBean videoReportingBean : videoReportingBeans) {
            assertEquals("Incorrect videos in day count", 1, videoReportingBean.getCount().intValue());
        }
    }

}
