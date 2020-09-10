package com.softserve.marathon.services.impl;

import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.exception.exceptions.MarathonNotFoundByIdException;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.repositories.MarathonRepository;
import com.softserve.marathon.services.MarathonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MarathonServiceImplTest {

    @Autowired
    private MarathonService marathonService;

    @MockBean
    private MarathonRepository marathonRepository;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        Marathon marathon1 = new Marathon();
        marathon1.setId(1L);
        marathon1.setTitle("marathon_01");

        Marathon marathon2 = new Marathon();
        marathon2.setId(2L);
        marathon2.setTitle("marathon_02");

        List<Marathon> allMarathons = List.of(marathon1, marathon2);

        Mockito.when(marathonRepository.findAll()).thenReturn(allMarathons);

        Mockito.when(marathonRepository.findById(1L)).thenReturn(Optional.of(marathon1));
        Mockito.when(marathonRepository.findById(2L)).thenReturn(Optional.of(marathon2));
    }

    @Test
    void getAllTest() {

        Marathon marathon1 = new Marathon();
        marathon1.setId(1L);
        marathon1.setTitle("marathon_01");

        Marathon marathon2 = new Marathon();
        marathon2.setId(2L);
        marathon2.setTitle("marathon_02");

        MarathonDto dto1 = modelMapper.map(marathon1, MarathonDto.class);
        MarathonDto dto2 = modelMapper.map(marathon2, MarathonDto.class);
        List<MarathonDto> expected = List.of(dto1, dto2);

        assertEquals(expected, marathonService.getAll());
    }

    @Test
    void getMarathonByIdTest() {

        Marathon marathon1Expected = new Marathon();
        marathon1Expected.setId(1L);
        marathon1Expected.setTitle("marathon_01");

        Marathon marathon2Expected = new Marathon();
        marathon2Expected.setId(2L);
        marathon2Expected.setTitle("marathon_02");

        assertEquals(marathon1Expected, marathonService.getById(1L));
        assertEquals(marathon2Expected, marathonService.getById(2L));
        assertThrows(MarathonNotFoundByIdException.class, () -> marathonService.getById(1000L));
    }

    @Test
    void updateMarathonTest() {

        Marathon update = new Marathon();
        update.setId(3L);
        update.setTitle("updated marathon");

        Marathon shouldUpdate = new Marathon();
        shouldUpdate.setId(3L);
        shouldUpdate.setTitle("marathon");

        Mockito.when(marathonRepository.findById(3L)).thenReturn(Optional.of(shouldUpdate));
        Mockito.when(marathonRepository.save(shouldUpdate)).thenReturn(shouldUpdate);
        MarathonDto dto = modelMapper.map(update, MarathonDto.class);
        assertEquals(update.getTitle(), dto.getTitle());
    }

    @Test
    void deleteMarathonTest() {
        assertThrows(MarathonNotFoundByIdException.class, () -> marathonService.getById(1000L));
    }

}