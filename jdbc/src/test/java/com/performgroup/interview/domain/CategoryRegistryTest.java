package com.performgroup.interview.domain;

import com.performgroup.interview.domain.impl.VideoCategoryImpl;
import org.junit.After;
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
public class CategoryRegistryTest {

    private static final String TEST_CATEGORY_NAME = "Test category name";

    @Autowired
    CategoryRegistry categoryRegistry;

    @Test
    public void testCreateCategory() throws Exception {

        VideoCategoryImpl videoCategory = categoryRegistry.createCategory(TEST_CATEGORY_NAME);

        assertNotNull(videoCategory.getId());
    }

    @After
    public void cleanup() {

        categoryRegistry.removeCategory(TEST_CATEGORY_NAME);
    }
}
