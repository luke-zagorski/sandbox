package pl.com.zagorski.boot.repository;

import org.springframework.data.repository.CrudRepository;
import pl.com.zagorski.boot.repository.model.Note;

/**
 * @author Luke Zagorski
 *         Date 12.01.2014
 */
public interface NoteRepository extends CrudRepository<Note, Long> {
}
