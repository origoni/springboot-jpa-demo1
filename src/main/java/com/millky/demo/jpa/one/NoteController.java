package com.millky.demo.jpa.one;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    NoteDao noteDao;

    public NoteController(NoteDao noteDao) {
        this.noteDao = noteDao;
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Note create(Note note) {
        noteDao.save(note);
        return note;
    }

    @GetMapping("/{id}")
    public Note read(@PathVariable int id) {
        return noteDao.findById(id);
    }

    @GetMapping
    public List<Note> list() {
        return noteDao.findAll();
    }

    @GetMapping("/criteria")
    public List<Note> listCriteria() {
        return noteDao.findAllCriteria();
    }


    @PatchMapping
    public Note update(Note note) {
        return noteDao.update(note);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public int delete(@PathVariable int id) {
        noteDao.deleteById(id);
        return id;
    }
}
