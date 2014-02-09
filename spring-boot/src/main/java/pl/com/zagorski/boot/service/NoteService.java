package pl.com.zagorski.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.zagorski.boot.repository.NoteRepository;
import pl.com.zagorski.boot.repository.model.Note;

/**
 * @author Luke Zagorski
 *         Date 26.01.2014
 */
@Service
public class NoteService {

  private NoteRepository noteRepository;

  @Autowired
  public NoteService(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  public void addNote(Note note) {

    noteRepository.save(note);
  }

  public Note findNote(long id) {
    return noteRepository.findOne(id);
  }

}
