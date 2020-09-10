package com.softserve.marathon.services.impl;

import com.softserve.marathon.dto.sprint.SprintDto;
import com.softserve.marathon.exception.exceptions.SprintNotFoundByIdException;
import com.softserve.marathon.mapper.SprintDtoMapper;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.Sprint;
import com.softserve.marathon.repositories.MarathonRepository;
import com.softserve.marathon.repositories.SprintRepository;
import com.softserve.marathon.services.SprintService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SprintServiceImplTest {

    @Autowired
    private SprintService sprintService;

    @MockBean
    private SprintRepository sprintRepository;

    @MockBean
    private MarathonRepository marathonRepository;

    @Autowired
    private SprintDtoMapper sprintMapper;

    @BeforeEach
    void setUp() {

        Marathon marathon = new Marathon();
        marathon.setId(1L);
        marathon.setTitle("marathon");

        Sprint sprint1 = new Sprint();
        sprint1.setId(1L);
        sprint1.setMarathon(marathon);
        sprint1.setTitle("sprint1");

        Sprint sprint2 = new Sprint();
        sprint2.setId(2L);
        sprint2.setMarathon(marathon);
        sprint2.setTitle("sprint2");

        Mockito.when(sprintRepository.findAllByMarathonId(marathon.getId()))
                .thenReturn(List.of(sprint1, sprint2));

    }

    @Test
    void getSprintsByMarathonIdTest() {

        Marathon marathon = new Marathon();
        marathon.setId(1L);
        marathon.setTitle("marathon");

        Sprint sprint1 = new Sprint();
        sprint1.setId(1L);
        sprint1.setMarathon(marathon);
        sprint1.setTitle("sprint1");

        Sprint sprint2 = new Sprint();
        sprint2.setId(2L);
        sprint2.setMarathon(marathon);
        sprint2.setTitle("sprint2");

        SprintDto dto1 = sprintMapper.convertToDto(sprint1);
        SprintDto dto2 = sprintMapper.convertToDto(sprint2);

        assertEquals(List.of(dto1, dto2), sprintService.getSprintsByMarathon(marathon.getId()));
    }

    @Test
    void getSprintByIdTest() {

        Sprint sprint = new Sprint();
        sprint.setId(1L);
        sprint.setTitle("sprint");

        Mockito.when(sprintRepository.findById(sprint.getId())).thenReturn(Optional.of(sprint));

        assertEquals(sprint, sprintService.getById(sprint.getId()));
        assertThrows(SprintNotFoundByIdException.class, () -> sprintService.getById(1000L));
    }


}