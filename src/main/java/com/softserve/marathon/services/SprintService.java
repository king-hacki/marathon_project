package com.softserve.marathon.services;

import com.softserve.marathon.dto.sprint.SprintDto;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.Sprint;

import java.util.List;

public interface SprintService extends CrudService<Sprint, SprintDto>  {
    List<SprintDto> getSprintsByMarathon(long marathonId);
    boolean addSprintToMarathon(Sprint sprint, Marathon marathon);
}
