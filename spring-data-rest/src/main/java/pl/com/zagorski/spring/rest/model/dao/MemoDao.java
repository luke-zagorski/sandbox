package pl.com.zagorski.spring.rest.model.dao;

import pl.com.zagorski.spring.rest.model.Memo;

import java.util.List;

/**
 * @author Luke Zagorski
 *         Date 28.01.2013
 */
public interface MemoDao {

  List<Memo> findAll();
}
