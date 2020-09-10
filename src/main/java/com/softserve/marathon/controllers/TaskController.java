package com.softserve.marathon.controllers;

import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.dto.sprint.SprintDto;
import com.softserve.marathon.dto.task.TaskDto;
import com.softserve.marathon.mapper.TaskDtoMapper;
import com.softserve.marathon.model.User;
import com.softserve.marathon.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/task")
@Slf4j
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;
    private TaskDtoMapper taskDtoMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')" +
            " or hasAuthority('ROLE_USER') and @userServiceImpl.isUserHaveTask(#sprintId)")
    @GetMapping("/{sprintId}")
    public ResponseEntity<List<TaskDto>> getTasksBySprintId(@PathVariable long sprintId) {
        log.info("tasks by sprint id: " + sprintId);
        return ok(taskService.getTasksBySprintId(sprintId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')" +
            " or hasAuthority('ROLE_USER') and @userServiceImpl.isUserHaveProgress(#taskId)")
    @GetMapping("/by_id/{taskId}")
    public ResponseEntity<TaskDto> getTasksById(@PathVariable long taskId) {
        log.info("task by id: " + taskId);
        return ok(taskDtoMapper.convertToDto(taskService.getById(taskId)));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<MarathonDto> delete(@PathVariable long taskId) {
        log.info("Delete task with id: " + taskId);
        taskService.deleteById(taskId);
        return ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<TaskDto> update(@Valid @RequestBody TaskDto dto) {
        log.info("update Task: " + dto);
        return ok(taskDtoMapper.convertToDto(taskService.update(dto)));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<TaskDto> save(@RequestBody TaskDto dto) {
        log.info("save Task: " + dto);
        return ok(taskDtoMapper.convertToDto(taskService.save(dto)));
    }

}
