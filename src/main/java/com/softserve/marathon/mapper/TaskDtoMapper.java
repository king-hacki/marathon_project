package com.softserve.marathon.mapper;

import com.softserve.marathon.dto.task.TaskDto;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.model.enums.TaskStatus;
import com.softserve.marathon.services.SprintService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskDtoMapper implements MapperToEntity<TaskDto, Task>, MapperToDto<Task, TaskDto> {

    private ModelMapper modelMapper;
    private SprintService sprintService;

    @Override
    public TaskDto convertToDto(Task entity) {
        TaskDto dto = modelMapper.map(entity, TaskDto.class);
        dto.setStatusName(entity.getStatus().name());
        dto.setSprintId(entity.getSprint().getId());
        return dto;
    }

    @Override
    public Task convertToEntity(TaskDto dto) {
        Task task = modelMapper.map(dto, Task.class);
        task.setStatus(TaskStatus.valueOf(dto.getStatusName()));
        task.setSprint(sprintService.getById(dto.getSprintId()));
        return task;
    }
}
