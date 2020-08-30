package com.softserve.marathon.services.impl;

import com.softserve.marathon.exceptions.SprintNotFoundByIdException;
import com.softserve.marathon.exceptions.TaskNotFoundByIdException;
import com.softserve.marathon.model.Sprint;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.repositories.SprintRepository;
import com.softserve.marathon.repositories.TaskRepository;
import com.softserve.marathon.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private SprintRepository sprintRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, SprintRepository sprintRepository) {
        this.taskRepository = taskRepository;
        this.sprintRepository = sprintRepository;
    }

    @Override
    public Task addTaskToSprint(long sprintId, Task task) {
        Sprint sprintEntity = sprintRepository.findById(sprintId)
                .orElseThrow(SprintNotFoundByIdException::new);
        Set<Task> tasksList = sprintEntity.getTasks();
        tasksList.add(taskRepository.save(task));
        return tasksList.stream()
                .filter(t -> t.equals(task))
                .findFirst().orElseThrow(TaskNotFoundByIdException::new);
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundByIdException::new);
    }
}
