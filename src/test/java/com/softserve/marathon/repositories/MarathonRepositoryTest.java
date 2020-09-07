package com.softserve.marathon.repositories;

import com.softserve.marathon.model.Marathon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MarathonRepositoryTest {

    @Autowired
    private MarathonRepository marathonRepository;

    @Test
    @Sql("file:src/test/resources/sql/marathon.sql")
    void testMarathonUpdate() {
        Marathon marathon = new Marathon();
        marathon.setId(1L);
        marathon.setTitle("foo_updated");

        Marathon actual;
        actual = marathonRepository.findById(1L).get();
        assertEquals("foo1", actual.getTitle());
        marathonRepository.save(marathon);
        actual = marathonRepository.findById(1L).get();
        assertEquals("foo_updated", actual.getTitle());
    }

    @Test
    @Sql("file:src/test/resources/sql/marathon.sql")
    void testMarathonsByUserID() {
        Marathon marathon = new Marathon();
        marathon.setId(1L);
        marathon.setTitle("foo1");

        List<Marathon> expected = List.of(marathon);
        List<Marathon> actual = marathonRepository.findAllByUsersId(1);
        assertEquals(expected, actual);

    }



}