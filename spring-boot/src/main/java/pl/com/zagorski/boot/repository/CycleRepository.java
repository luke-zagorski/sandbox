package pl.com.zagorski.boot.repository;

import org.springframework.data.repository.CrudRepository;
import pl.com.zagorski.boot.repository.model.Cycle;

/**
 * @author Luke Zagorski
 *         Date 12.01.2014
 */
public interface CycleRepository extends CrudRepository<Cycle, Long> {
}
