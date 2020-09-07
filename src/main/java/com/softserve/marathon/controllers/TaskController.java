package com.softserve.marathon.controllers;

import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.dto.task.TaskDto;
import com.softserve.marathon.mapper.TaskDtoMapper;
import com.softserve.marathon.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/task")
@Slf4j
@CrossOrigin("*")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;
    private TaskDtoMapper taskDtoMapper;

    @GetMapping("/{sprintId}")
    public ResponseEntity<?> getTasksBySprintId(@PathVariable long sprintId) {
        log.info("tasks by sprint id: " + sprintId);
        return ok(taskService.getTasksBySprintId(sprintId));
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<MarathonDto> delete(@PathVariable long taskId) {
        log.info("Delete task with id: " + taskId);
        taskService.deleteById(taskId);
        return ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<TaskDto> update(@Valid @RequestBody TaskDto dto) {
        log.info("update Task: " + dto);
        return ok(taskDtoMapper.convertToDto(taskService.update(dto)));
    }

    @PostMapping("/save")
    public ResponseEntity<TaskDto> save(@RequestBody TaskDto dto) {
        log.info("save Task: " + dto);
        return ok(taskDtoMapper.convertToDto(taskService.save(dto)));
    }
}
