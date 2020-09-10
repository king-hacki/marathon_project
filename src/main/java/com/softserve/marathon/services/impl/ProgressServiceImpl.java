package com.softserve.marathon.services.impl;

import com.softserve.marathon.dto.progress.ProgressDto;
import com.softserve.marathon.dto.progress.SaveProgressDto;
import com.softserve.marathon.dto.progress.TaskAndSolutionDto;
import com.softserve.marathon.dto.progress.UserIdAndTaskIdDto;
import com.softserve.marathon.exception.exceptions.ProgressNotFoundByIdException;
import com.softserve.marathon.mapper.ProgressDtoMapper;
import com.softserve.marathon.model.Progress;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.model.User;
import com.softserve.marathon.repositories.ProgressRepository;
import com.softserve.marathon.services.ProgressService;
import com.softserve.marathon.services.TaskService;
import com.softserve.marathon.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private ProgressRepository progressRepository;
    private ProgressDtoMapper progressDtoMapper;
    private UserService userService;
    private TaskService taskService;
    private ModelMapper modelMapper;

    @Override
    public Progress addTaskForStudent(Task task, User user) {
        Progress progress = new Progress();
        progress.setTask(task);
        progress.setUser(user);
        Progress progressEntity = progressRepository.save(progress);
        task.getProgresses().add(progressEntity);
        user.getProgresses().add(progressEntity);
        return progressEntity;
    }

    @Override
    public List<Progress> allProgressesByUserAndMarathonId(long userId, long marathonId) {
        return progressRepository.findAllByUserIdAndTaskSprintMarathonId(userId, marathonId);
    }

    @Override
    public List<Progress> allProgressesByUserAndSprintId(long userId, long sprintId) {
        return progressRepository.findAllByUserIdAndTaskSprintId(userId, sprintId);
    }

    @Override
    public List<ProgressDto> getProgressesByTaskId(long taskId) {
        return progressRepository.findAllByTaskId(taskId).stream()
                .map(progress -> progressDtoMapper.convertToDto(progress))
                .collect(toList());
    }

    @Override
    public List<ProgressDto> getProgressesByUserId(long userId) {
        return progressRepository.findAllByUserId(userId).stream()
                .map(progress -> progressDtoMapper.convertToDto(progress))
                .collect(toList());
    }

    @Override
    public TaskAndSolutionDto solutionByProgressId(long progressId) {
        Progress progressEntity = getById(progressId);
        return TaskAndSolutionDto.builder()
                .solution(progressEntity.getSolution())
                .task(progressEntity.getTask().getTaskDescription())
                .build();
    }

    @Override
    public ProgressDto getProgressByUserIdAndTaskId(UserIdAndTaskIdDto dto) {
        User userEntity = userService.getById(dto.getUserId());
        Task taskEntity = taskService.getById(dto.getTaskId());
        Progress progress = progressRepository.findByUserIdAndTaskId(userEntity.getId(), taskEntity.getId());
        if (progress == null) return null;
        return progressDtoMapper.convertToDto(progress);
    }

    @Override
    public Progress getById(long progressId) {
        return progressRepository.findById(progressId)
                .orElseThrow(ProgressNotFoundByIdException::new);
    }

    @Override
    public void deleteById(long progressId) {
        progressRepository.deleteById(progressId);
    }

    @Override
    public Progress update(ProgressDto dto) {
        getById(dto.getId());
        return progressRepository.save(progressDtoMapper.convertToEntity(dto));
    }

    @Override
    public Progress save(ProgressDto dto) {
        Progress progressEntity = progressRepository.save(progressDtoMapper.convertToEntity(dto));
        Task taskEntity = taskService.getById(dto.getTaskId());
        taskEntity.getProgresses().add(progressEntity);
        User userEntity = userService.getUserByMail(dto.getUserMail());
        userEntity.getProgresses().add(progressEntity);
        return progressEntity;
    }

    @Override
    public ProgressDto updateByUserIdSolutionAndTaskId(SaveProgressDto dto) {
        UserIdAndTaskIdDto userIdAndTaskIdDto = modelMapper.map(dto, UserIdAndTaskIdDto.class);
        Progress progressEntity = progressDtoMapper.convertToEntity(getProgressByUserIdAndTaskId(userIdAndTaskIdDto));
        progressEntity.setSolution(dto.getSolution());
        return progressDtoMapper.convertToDto(progressRepository.save(progressEntity));
    }

    @Override
    public ProgressDto saveByUserIdSolutionAndTaskId(SaveProgressDto dto) {
        Task taskEntity = taskService.getById(dto.getTaskId());
        User userEntity = userService.getById(dto.getUserId());
        Progress progress = new Progress();
        progress.setTask(taskEntity);
        progress.setUser(userEntity);
        progress.setSolution(dto.getSolution());
        progress.setStarted(LocalDate.now());
        Progress progressEntity = progressRepository.save(progress);
        return progressDtoMapper.convertToDto(progressEntity);
    }
}
