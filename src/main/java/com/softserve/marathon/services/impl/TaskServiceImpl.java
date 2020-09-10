package com.softserve.marathon.services.impl;

import com.softserve.marathon.dto.task.TaskDto;
import com.softserve.marathon.exception.exceptions.TaskNotFoundByIdException;
import com.softserve.marathon.mapper.TaskDtoMapper;
import com.softserve.marathon.model.Sprint;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.model.enums.TaskStatus;
import com.softserve.marathon.repositories.TaskRepository;
import com.softserve.marathon.services.SprintService;
import com.softserve.marathon.services.TaskService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private SprintService sprintService;
    private ModelMapper modelMapper;
    private TaskDtoMapper taskDtoMapper;

    @Override
    public Task addTaskToSprint(long sprintId, Task task) {
        Sprint sprintEntity = sprintService.getById(sprintId);
        Set<Task> tasksList = sprintEntity.getTasks();
        tasksList.add(taskRepository.save(task));
        return tasksList.stream()
                .filter(t -> t.equals(task))
                .findFirst().orElseThrow(TaskNotFoundByIdException::new);
    }

    @Override
    public Task getById(long id) {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundByIdException::new);
    }

    @Override
    public boolean setStatus(TaskStatus status, Task task) {
        try {
            Task taskEntity = getById(task.getId());
            taskEntity.setStatus(status);
            taskRepository.save(taskEntity);
            return true;
        } catch (TaskNotFoundByIdException e) {
            return false;
        }
    }

    @Override
    public List<TaskDto> getTasksBySprintId(long sprintId) {
        List<Task> tasksBySprintId = taskRepository.findAllBySprintId(sprintId);
        return tasksBySprintId.stream()
                .map(task -> taskDtoMapper.convertToDto(task))
                .collect(toList());
    }

    @Override
    public void deleteById(long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task update(TaskDto dto) {
        getById(dto.getId());
        return taskRepository.save(taskDtoMapper.convertToEntity(dto));
    }

    @Override
    public Task save(TaskDto dto) {
        Task taskEntity = taskRepository.save(taskDtoMapper.convertToEntity(dto));
        return addTaskToSprint(dto.getSprintId(), taskEntity);
    }
}
