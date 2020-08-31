package com.softserve.marathon.services;

import com.softserve.marathon.exceptions.ProgressNotFoundByIdException;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.Progress;
import com.softserve.marathon.model.Sprint;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.model.enums.TaskStatus;

import java.util.List;

public interface SprintService {
    List<Sprint> getSprintsByMarathon(long marathonId);
    boolean addSprintToMarathon(Sprint sprint, Marathon marathon);
    Sprint updateOrSaveSprint(Sprint sprint);
    Sprint getSprintById(long sprintId);
}
