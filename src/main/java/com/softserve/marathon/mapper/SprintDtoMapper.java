package com.softserve.marathon.mapper;

import com.softserve.marathon.dto.sprint.SprintDto;
import com.softserve.marathon.model.Sprint;
import com.softserve.marathon.services.MarathonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SprintDtoMapper implements MapperToDto<Sprint, SprintDto>, MapperToEntity<SprintDto, Sprint> {

    private ModelMapper modelMapper;
    private MarathonService marathonService;

    @Override
    public SprintDto convertToDto(Sprint entity) {
        SprintDto dto = modelMapper.map(entity, SprintDto.class);
        dto.setMarathonId(entity.getMarathon().getId());
        return dto;
    }

    @Override
    public Sprint convertToEntity(SprintDto dto) {
        Sprint sprint = modelMapper.map(dto, Sprint.class);
        sprint.setMarathon(marathonService.getById(dto.getMarathonId()));
        return sprint;
    }
}
