package com.softserve.marathon.controllers;

import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.dto.progress.ProgressDto;
import com.softserve.marathon.dto.progress.TaskAndSolutionDto;
import com.softserve.marathon.services.ProgressService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/progress")
@Slf4j
@CrossOrigin("*")
public class ProgressController {

    private ProgressService progressService;

    @Autowired
    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/by_task/{taskId}")
    public ResponseEntity<List<ProgressDto>> getProgressesByTaskId(@PathVariable long taskId) {
        log.info("Progresses by task id: " + taskId);
        return ok(progressService.getProgressesByTaskId(taskId));
    }

    @GetMapping("/by_user/{userId}")
    public ResponseEntity<List<ProgressDto>> getProgressesByUserId(@PathVariable long userId) {
        log.info("get progress by user ID: " + userId);
        return ok(progressService.getProgressesByUserId(userId));
    }

    @GetMapping("/solution_view/{progressId}")
    public ResponseEntity<TaskAndSolutionDto> solutionByProgressId(@PathVariable long progressId) {
        log.info("get solution by progress ID: " + progressId);
        return ok(progressService.solutionByProgressId(progressId));
    }

}
