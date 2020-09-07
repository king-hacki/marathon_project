package com.softserve.marathon.services;


import com.softserve.marathon.dto.task.TaskDto;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.model.enums.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService extends CrudService<Task, TaskDto> {
    Task addTaskToSprint(long sprintId, Task task);
    boolean setStatus(TaskStatus status, Task task);
    List<TaskDto> getTasksBySprintId(long sprintId);
}
