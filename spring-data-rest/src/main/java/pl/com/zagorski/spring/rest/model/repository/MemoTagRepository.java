package pl.com.zagorski.spring.rest.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.repository.annotation.RestResource;
import pl.com.zagorski.spring.rest.model.MemoTag;

/**
 * @author Luke Zagorski
 *         Date 02.02.2013
 */
@RestResource
public interface MemoTagRepository extends CrudRepository<MemoTag, Long> {
}
