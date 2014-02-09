package com.performgroup.interview.dto.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;

/**
 * @author : luke
 * @version : 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:testApplicationContext.xml"})
public class VideoMapperTest {

    private static final String VIDEO_1_XML = "video_1.xml";
    @Autowired
    VideoMapper videoMapper;

    @Test
    public void testMarshallerInjection() {

        assertNotNull(videoMapper.getUnmarshaller());
    }

    @Test
    public void loadXmlTest() throws Exception {

        videoMapper.unmarshallXml(VIDEO_1_XML);

        assertNotNull("Unmarshalling Error", videoMapper.getDto());
    }
}
