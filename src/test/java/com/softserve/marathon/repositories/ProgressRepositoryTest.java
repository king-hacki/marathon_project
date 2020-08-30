package com.softserve.marathon.repositories;

import com.softserve.marathon.model.Progress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProgressRepositoryTest {

    @Autowired
    private ProgressRepository progressRepository;

    @Test
    @Sql("file:src/test/resources/sql/progress_by_userId_and_marathonId.sql")
    void progressByUserIdAndMarathonId() {
        List<Progress> expected;
        expected = progressRepository.findAllByUserIdAndTaskSprintId(2, 1);
        assertEquals(0, expected.size());
        expected = progressRepository.findAllByUserIdAndTaskSprintMarathonId(1, 1);
        assertEquals(2, expected.size());
        assertEquals(1, expected.get(0).getId());
        assertEquals(2, expected.get(1).getId());
        expected = progressRepository.findAllByUserIdAndTaskSprintMarathonId(1, 2);
        assertEquals(1, expected.size());
        assertEquals(3, expected.get(0).getId());
    }

    @Test
    @Sql("file:src/test/resources/sql/progress_by_userId_and_marathonId.sql")
    void progressByUserIdAndSprintId() {
        List<Progress> expected;
        expected = progressRepository.findAllByUserIdAndTaskSprintId(2, 1);
        assertEquals(0, expected.size());
        expected = progressRepository.findAllByUserIdAndTaskSprintId(1, 1);
        assertEquals(2, expected.size());
        assertEquals(1, expected.get(0).getId());
        assertEquals(2, expected.get(1).getId());
        expected = progressRepository.findAllByUserIdAndTaskSprintId(1, 2);
        assertEquals(1, expected.size());
        assertEquals(3, expected.get(0).getId());
    }

}