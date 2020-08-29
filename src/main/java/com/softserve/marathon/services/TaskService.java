package com.softserve.marathon.services;


import com.softserve.marathon.model.Sprint;
import org.springframework.scheduling.config.Task;

public interface TaskService {
    Task addTaskToSprint(Task task, Sprint sprint);
    Task getTaskById(long id);
}
