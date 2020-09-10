package com.softserve.marathon.services.impl;

import com.softserve.marathon.dto.progress.ProgressDto;
import com.softserve.marathon.dto.progress.SaveProgressDto;
import com.softserve.marathon.dto.progress.UserIdAndTaskIdDto;
import com.softserve.marathon.exception.exceptions.ProgressNotFoundByIdException;
import com.softserve.marathon.mapper.ProgressDtoMapper;
import com.softserve.marathon.model.Progress;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.model.User;
import com.softserve.marathon.repositories.ProgressRepository;
import com.softserve.marathon.repositories.TaskRepository;
import com.softserve.marathon.repositories.UserRepository;
import com.softserve.marathon.services.ProgressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProgressServiceImplTest {

    @MockBean
    private ProgressRepository progressRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private ProgressServiceImpl progressService;

    @Autowired
    private ProgressDtoMapper progressMapper;

    @BeforeEach
    void setUp() {

        Progress progress = new Progress();
        progress.setId(1L);

        Task task = new Task();
        task.setId(1L);
        task.setTitle("task");

        User user = new User();
        user.setId(1L);
        user.setLastName("last");
        user.setFirstName("first");
        user.setEmail("test@gmail.com");
        user.setPassword("pass");

        Progress addTaskAndUserProgress = new Progress();
        addTaskAndUserProgress.setUser(user);
        addTaskAndUserProgress.setTask(task);

        Mockito.when(progressRepository.findById(progress.getId())).thenReturn(Optional.of(progress));
        Mockito.when(progressRepository.save(progress)).thenReturn(progress);
        Mockito.when(progressRepository.save(addTaskAndUserProgress)).thenReturn(addTaskAndUserProgress);

    }

    @Test
    void getProgressByIdTest() {
        Progress expected = new Progress();
        expected.setId(1L);
        assertEquals(expected, progressService.getById(1L));
        assertThrows(ProgressNotFoundByIdException.class, () -> progressService.getById(1000L));
    }

    @Test
    void progressByUserIdAndTaskIdTest() {

        Task task = new Task();
        task.setId(1L);
        task.setTitle("task");

        User user = new User();
        user.setId(1L);
        user.setLastName("last");
        user.setFirstName("first");
        user.setEmail("test@gmail.com");
        user.setPassword("pass");

        Progress expected = new Progress();
        expected.setId(1L);
        expected.setSolution("solution");
        expected.setUser(user);
        expected.setTask(task);

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        Mockito.when(progressRepository
                .findByUserIdAndTaskId(user.getId(), task.getId())).thenReturn(expected);

        UserIdAndTaskIdDto userIdAndTaskIdDto = new UserIdAndTaskIdDto(user.getId(), task.getId());
        ProgressDto actualDto = progressService.getProgressByUserIdAndTaskId(userIdAndTaskIdDto);
        Progress actual = progressMapper.convertToEntity(actualDto);
        assertEquals(expected, actual);
    }


    @Test
    void saveByUserIdSolutionAndTaskIdTest() {
        SaveProgressDto saveProgressDto = new SaveProgressDto("solution", 1L, 1L);

        Task task = new Task();
        task.setId(1L);
        task.setTitle("task");

        User user = new User();
        user.setId(1L);
        user.setLastName("last");
        user.setFirstName("first");
        user.setEmail("test@gmail.com");
        user.setPassword("pass");

        Progress expected = new Progress();
        expected.setTask(task);
        expected.setUser(user);
        expected.setSolution(saveProgressDto.getSolution());
        expected.setStarted(LocalDate.now());

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(userRepository.findById(saveProgressDto.getUserId())).thenReturn(Optional.of(user));
        Mockito.when(taskRepository.findById(saveProgressDto.getTaskId())).thenReturn(Optional.of(task));
        Mockito.when(progressRepository.save(expected)).thenReturn(expected);

        ProgressDto actualDto = progressService.saveByUserIdSolutionAndTaskId(saveProgressDto);
        Progress actual = progressMapper.convertToEntity(actualDto);

        assertEquals(expected.getUser(), actual.getUser());
        assertEquals(expected.getSolution(), actual.getSolution());
        assertEquals(expected.getTask(), actual.getTask());
    }
}