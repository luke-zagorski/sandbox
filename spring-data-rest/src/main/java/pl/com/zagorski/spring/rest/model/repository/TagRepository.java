package pl.com.zagorski.spring.rest.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.repository.annotation.RestResource;
import pl.com.zagorski.spring.rest.model.Tag;

import java.util.List;

/**
 * @author Luke Zagorski
 *         Date 30.01.2013
 */
@RestResource
public interface TagRepository extends CrudRepository<Tag, Long> {

  public List<Tag> findByName(@Param("name") String name);


}
