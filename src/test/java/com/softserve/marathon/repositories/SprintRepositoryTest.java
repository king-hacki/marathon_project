package com.softserve.marathon.repositories;

import com.softserve.marathon.model.Sprint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SprintRepositoryTest {

    @Autowired
    private SprintRepository sprintRepository;

    @Test
    @Sql("file:src/test/resources/sql/progress_by_userId_and_marathonId.sql")
    void sprintsByMarathonIdTest() {
        List<Sprint> expected;
        expected = sprintRepository.findAllByMarathonId(1);
        assertEquals(2, expected.size());
        assertEquals(1, expected.get(0).getId());
        assertEquals(3, expected.get(1).getId());
        expected = sprintRepository.findAllByMarathonId(100);
        assertEquals(0, expected.size());
    }

}