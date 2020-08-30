package com.softserve.marathon.services;

import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.Sprint;

import java.util.List;

public interface SprintService {
    List<Sprint> getSprintsByMarathon(long marathonId);
    boolean addSprintToMarathon(Sprint sprint, Marathon marathon);
    Sprint updateOrSaveSprint(Sprint sprint);
    Sprint getSprintById(long sprintId);
}
