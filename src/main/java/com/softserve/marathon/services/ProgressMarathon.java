package com.softserve.marathon.services;

import com.softserve.marathon.model.Progress;
import com.softserve.marathon.model.User;
import com.softserve.marathon.model.enums.TaskStatus;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface ProgressMarathon {

    Progress getProgressById(long id);
    Progress addTaskForStudent(Task task, User user);
    Progress addOrUpdateProgress(Progress progress);
    boolean setStatus(TaskStatus status, Progress progress);
    List<Progress> allProgressesByUserAndMarathonId(long userId, long marathonId);
    List<Progress> allProgressesByUserAndSprintId(long userId, long sprintId);

}
