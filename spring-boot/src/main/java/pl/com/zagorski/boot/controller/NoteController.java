package pl.com.zagorski.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.com.zagorski.boot.repository.model.Note;
import pl.com.zagorski.boot.service.NoteService;

/**
 * @author Luke Zagorski
 *         Date 26.01.2014
 */
@Controller
public class NoteController {

  @Autowired
  private NoteService noteService;

  @ResponseBody
  @RequestMapping(value = "/note/{id}", method = {RequestMethod.GET})
  public String getNote(@PathVariable String id) {

    return noteService.findNote(Long.valueOf(id)).toString();
  }

  @RequestMapping(value = "/note/{id}", method = {RequestMethod.PUT})
  public ResponseEntity<HttpStatus> addNote(@RequestBody String note) {

    noteService.addNote(new Note(note));

    return new ResponseEntity<HttpStatus>(HttpStatus.OK);
  }


  public void setNoteService(NoteService noteService) {
    this.noteService = noteService;
  }
}
