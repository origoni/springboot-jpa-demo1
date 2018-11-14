package com.millky.demo.jpa.one;

//import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Transactional
@Repository
public class NoteDao {

    @PersistenceContext
    private EntityManager entityManager;


    public Note save(Note note) {
        note.setCreatedAt(ZonedDateTime.now());
        entityManager.persist(note);
        return note;
    }

    public Note update(Note form) {
        Note note = findById(form.getId());
        note.setName(form.getName());
        note.setContent(form.getContent());
        note.setUpdatedAt(ZonedDateTime.now());
        return entityManager.merge(note);
    }

    public void delete(Note note) {
        entityManager.remove(note);
    }

    public void deleteById(int id) {
        Note note = findById(id);
        delete(note);
    }

    public Note findById(int primaryKey) {
        return entityManager.find(Note.class, primaryKey);
    }

    public List<Note> findAll() {
//        TypedQuery<Note> query = entityManager.createQuery("SELECT n FROM Note n WHERE n.id > :idParam", Note.class);
//        query.setParameter("idParam", 1);
        TypedQuery<Note> query = entityManager.createQuery("SELECT n FROM Note n", Note.class);
        return query.getResultList();
    }


    public List<Note> findAllCriteria() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Note> query = builder.createQuery(Note.class);
        Root<Note> root = query.from(Note.class);
//        query.where(builder.or(
//                builder.equal(root.get("name"), "dk.lee"),
//                builder.equal(root.get("name"), "origoni")
//        ));
        return entityManager.createQuery(query.select(root)).getResultList();

    }
}
