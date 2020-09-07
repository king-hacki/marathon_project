package com.softserve.marathon.mapper;

import com.softserve.marathon.dto.progress.ProgressDto;
import com.softserve.marathon.model.Progress;
import com.softserve.marathon.repositories.UserRepository;
import com.softserve.marathon.services.TaskService;
import com.softserve.marathon.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProgressDtoMapper implements MapperToDto<Progress, ProgressDto>,
        MapperToEntity<ProgressDto, Progress> {

    private ModelMapper modelMapper;
    private TaskService taskService;
    private UserRepository userRepository;

    @Override
    public ProgressDto convertToDto(Progress entity) {
        ProgressDto dto = modelMapper.map(entity, ProgressDto.class);
        dto.setUserMail(entity.getUser().getEmail());
        dto.setTaskId(entity.getTask().getId());
        return dto;
    }

    @Override
    public Progress convertToEntity(ProgressDto dto) {
        Progress progress = modelMapper.map(dto, Progress.class);
        progress.setUser(userRepository.findByEmail(dto.getUserMail()));
        progress.setTask(taskService.getById(dto.getTaskId()));
        return progress;
    }
}
