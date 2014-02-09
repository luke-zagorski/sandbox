package com.performgroup.interview.dao;

import com.performgroup.interview.dao.domain.VideoEntity;
import com.performgroup.interview.dao.domain.VideoType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author : luke
 * @version : 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:testApplicationContext.xml"})
public class VideoDAOTest {

    @Autowired
    VideoDAO videoDAO;

    @Test
    public void testFindAll() throws Exception {

        // Given - Fixtures in "performdb_test.script"

        // When
        Collection<VideoEntity> videoEntities = videoDAO.findAll();

        // Then
        assertEquals("Video find all error", 2, videoEntities.size());

    }

    @Test
    public void testFindById() throws Exception {

        // Given - Fixtures in "performdb_test.script"

        // When
        VideoEntity videoEntity = videoDAO.findById(1l);

        // Then
        assertNotNull("Video find by id error", videoEntity);
    }

    @Test
    public void testCreateVideo() throws Exception {

        // When
        VideoEntity videoEntity = videoDAO.createVideo("testTitle", "testPath", VideoType.FRUIT_MATCH);

        // Then
        assertNotNull("Video persist error", videoEntity.getId());

        // Cleanup
        videoDAO.delete("testTitle");
    }

    @Test
    public void testFindByTitle() throws Exception {

        // Given - Fixtures in "performdb_test.script"

        // When
        VideoEntity videoEntity = videoDAO.findByTitle("Peaches v Pears");

        // Then
        assertNotNull("Video find by title error", videoEntity);
    }

}
