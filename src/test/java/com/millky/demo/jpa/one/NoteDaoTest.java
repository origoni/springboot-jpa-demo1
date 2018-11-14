package com.millky.demo.jpa.one;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteDaoTest {

    @Autowired
    NoteDao noteDao;


    @Before
    public void setUp() {
        Note note = new Note();
        note.setContent("test");
        Note result = noteDao.save(note);
        System.out.println(result);
    }

    @Test
    public void findAll() {
        Note note = new Note();
        note.setContent("test2");
        Note result2 = noteDao.save(note);
        System.out.println(result2);

        assertThat(noteDao.findAll())
                .flatExtracting(s -> asList(s.getId(), s.getContent()))
                .contains(1, "test", 2, "test2");
    }
}
