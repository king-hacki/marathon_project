package com.softserve.marathon.services.impl;

import com.softserve.marathon.dto.sprint.SprintDto;
import com.softserve.marathon.exception.exceptions.SprintNotFoundByIdException;
import com.softserve.marathon.mapper.SprintDtoMapper;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.Sprint;
import com.softserve.marathon.repositories.SprintRepository;
import com.softserve.marathon.services.MarathonService;
import com.softserve.marathon.services.SprintService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor
public class SprintServiceImpl implements SprintService {

    private SprintRepository sprintRepository;
    private MarathonService marathonService;
    private SprintDtoMapper sprintDtoMapper;

    @Override
    public List<SprintDto> getSprintsByMarathon(long marathonId) {
        List<Sprint> sprintsByMarathonId = sprintRepository.findAllByMarathonId(marathonId);
        return sprintsByMarathonId.stream()
                .map(sprint -> sprintDtoMapper.convertToDto(sprint))
                .collect(toList());
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
    public Sprint getById(long sprintId) {
        return sprintRepository.findById(sprintId)
                .orElseThrow(SprintNotFoundByIdException::new);
    }

    @Override
    public void deleteById(long sprintId) {
        sprintRepository.deleteById(sprintId);
    }

    @Override
    public Sprint update(SprintDto dto) {
        getById(dto.getId());
        return sprintRepository.save(sprintDtoMapper.convertToEntity(dto));
    }

    @Override
    public Sprint save(SprintDto dto) {
        Sprint sprintEntity = sprintRepository.save(sprintDtoMapper.convertToEntity(dto));
        Marathon marathonEntity = marathonService.getById(dto.getMarathonId());
        addSprintToMarathon(sprintEntity, marathonEntity);
        return sprintEntity;
    }
}
