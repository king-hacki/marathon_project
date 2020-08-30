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
        List<Sprint> allByMarathonId = sprintRepository.findAllByMarathonId(1);
        assertEquals(2, allByMarathonId.size());
        assertEquals(1, allByMarathonId.get(0).getId());
        assertEquals(3, allByMarathonId.get(1).getId());
    }

}