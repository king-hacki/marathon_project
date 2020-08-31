package com.softserve.marathon.services;

import com.softserve.marathon.model.Progress;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProgressService {

    Progress getProgressById(long id);
    Progress addTaskForStudent(Task task, User user);
    Progress addOrUpdateProgress(Progress progress);
    List<Progress> allProgressesByUserAndMarathonId(long userId, long marathonId);
    List<Progress> allProgressesByUserAndSprintId(long userId, long sprintId);

}
