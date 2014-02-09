package com.performgroup.interview.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNull;

/**
 * @author : luke
 * @version : 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:testApplicationContext.xml"})
public class CategoryHibernateDAOTest {

    @Autowired
    CategoryDAO categoryDAO;

    @Test
    public void testFindByName() throws Exception {

        assertNull(categoryDAO.findByName("NonExistingName"));
    }
}
