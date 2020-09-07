package com.softserve.marathon.services;

import com.softserve.marathon.dto.progress.ProgressDto;
import com.softserve.marathon.dto.progress.TaskAndSolutionDto;
import com.softserve.marathon.model.Progress;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProgressService extends CrudService<Progress, ProgressDto> {

    Progress addTaskForStudent(Task task, User user);
    List<Progress> allProgressesByUserAndMarathonId(long userId, long marathonId);
    List<Progress> allProgressesByUserAndSprintId(long userId, long sprintId);
    List<ProgressDto> getProgressesByTaskId(long taskId);
    List<ProgressDto> getProgressesByUserId(long userId);
    TaskAndSolutionDto solutionByProgressId(long progressId);
}
