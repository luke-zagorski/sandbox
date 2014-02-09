package com.performgroup.interview.service;

import com.performgroup.interview.dao.domain.VideoReportingBean;
import com.performgroup.interview.dao.domain.VideoType;
import com.performgroup.interview.dto.VideoDto;
import com.performgroup.interview.dto.VideoReportSummaryDto;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * User: luke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:testApplicationContext.xml"})
public class VideoServiceImplTest extends TestCase {

    public static final String NONE_EXISTING_TYPE = "NONE_EXISTING_TYPE";
    @Autowired
    VideoService videoService;

    private final VideoDto newTestVideoDto = new VideoDto("TestVideoEntity", "path", VideoType.DRINK_MATCH, "newCategory", "Sample");
    private final VideoDto existingTestVideoDto = new VideoDto("Peaches v Pears", "rtmp://asdf.fr.24.com", VideoType.FRUIT_MATCH, "newCategory", "Sample");

    @Before
    public void checkFixtures() {
        assertEquals(2, videoService.listVideos().size());
    }

    @Test
    public void tesCreateVideosAndExistingListSize() throws Exception {

        // Given - Fixtures in "performdb_test.script"

        //When
        createOneNewTestVideo();

        //Then
        assertEquals(3, videoService.listVideos().size());

        //Cleanup
        removeTestVideo();
    }

    @Test
    public void tesUpdateVideosAndExistingListSize() throws Exception {

        // Given - Fixtures in "performdb_test.script"
        Date oldCreationDate = videoService.getVideo(1l).getCreationDate();

        //When
        videoService.addVideo(existingTestVideoDto);

        //Then
        Date creationDate = videoService.getVideo(1l).getCreationDate();
        assertEquals(oldCreationDate, creationDate);

        assertEquals(2, videoService.listVideos().size());
    }


    @Test
    public void testVideoSummaryReport() {

        // Given - Fixtures in "performdb_test.script"

        //When
        VideoReportSummaryDto videoReportSummaryDto = videoService.getVideoSummaryReport();

        //Then
        assertNotNull(videoReportSummaryDto);
        assertEquals(videoReportSummaryDto.getDayReport().size(), 2);
        assertEquals(videoReportSummaryDto.getTypeReport().size(), 2);
    }

    @Test
    public void testVideoReport() {

        // Given - Fixtures in "performdb_test.script"

        //When
        VideoReportingBean videoReportingBean = videoService.getVideoReport("FRUIT_MATCH");

        // Then
        assertNotNull(videoReportingBean);
        assertEquals(VideoType.FRUIT_MATCH.name(), videoReportingBean.getDescription());
        assertEquals(1, videoReportingBean.getCount().intValue());
    }

    @Test
    public void testNoEnumVideoReport() {

        // Given - Fixtures in "performdb_test.script"

        //When
        VideoReportingBean videoReportingBean = videoService.getVideoReport(NONE_EXISTING_TYPE);

        assertNotNull(videoReportingBean);
        assertEquals(0, videoReportingBean.getCount().intValue());
        assertEquals(NONE_EXISTING_TYPE, videoReportingBean.getDescription());

    }


    /*
         HELPERS
    */
    public void createOneNewTestVideo() throws Exception {

        videoService.addVideo(newTestVideoDto);
    }

    public void removeTestVideo() {

        videoService.deleteVideo(newTestVideoDto.getTitle());
    }
}
