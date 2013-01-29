package pl.com.zagorski.spring.rest.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Luke Zagorski
 *         Date 28.01.2013
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class MemoDaoImplTest {


  @Resource
  MemoDao memoDao;

  @Test
  public void testFindAll() throws Exception {

    memoDao.findAll();

  }
}
