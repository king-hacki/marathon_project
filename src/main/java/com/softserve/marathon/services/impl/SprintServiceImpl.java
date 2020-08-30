package com.softserve.marathon.services.impl;

import com.softserve.marathon.exceptions.SprintNotFoundByIdException;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.Sprint;
import com.softserve.marathon.repositories.SprintRepository;
import com.softserve.marathon.services.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SprintServiceImpl implements SprintService {

    private SprintRepository sprintRepository;

    @Autowired
    public SprintServiceImpl(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override
    public List<Sprint> getSprintsByMarathon(long marathonId) {
        return sprintRepository.findAllByMarathonId(marathonId);
    }

    @Override
    public boolean addSprintToMarathon(Sprint sprint, Marathon marathon) {
        try {
            sprintRepository.findById(sprint.getId());
            marathon.getSprints().add(sprint);
            return true;
        } catch (SprintNotFoundByIdException e) {
            return false;
        }
    }

    @Override
    public Sprint updateOrSaveSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint getSprintById(long sprintId) {
        return sprintRepository.findById(sprintId)
                .orElseThrow(SprintNotFoundByIdException::new);
    }
}
