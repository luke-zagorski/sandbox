package pl.com.zagorski.spring.rest.model.dao;

import pl.com.zagorski.spring.rest.model.Memo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Luke Zagorski
 *         Date 28.01.2013
 */
public class MemoDaoImpl implements MemoDao {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Memo> findAll() {

    Query query = entityManager.createQuery("FROM Memo AS m");
    return query.getResultList();
  }
}
