package com.softserve.marathon.services;


import com.softserve.marathon.model.Sprint;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.model.enums.TaskStatus;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    Task addTaskToSprint(long sprintId, Task task);
    Task getTaskById(long id);
    boolean setStatus(TaskStatus status, Task task);
}
