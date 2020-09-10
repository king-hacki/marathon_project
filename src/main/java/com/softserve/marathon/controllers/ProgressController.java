package com.softserve.marathon.controllers;

import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.dto.progress.ProgressDto;
import com.softserve.marathon.dto.progress.SaveProgressDto;
import com.softserve.marathon.dto.progress.TaskAndSolutionDto;
import com.softserve.marathon.dto.progress.UserIdAndTaskIdDto;
import com.softserve.marathon.model.User;
import com.softserve.marathon.services.ProgressService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/progress")
@Slf4j
public class ProgressController {

    private ProgressService progressService;

    @Autowired
    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') " +
            "or hasAuthority('ROLE_USER') and @userServiceImpl.isUserHaveProgress(#taskId)")
    @GetMapping("/by_task/{taskId}")
    public ResponseEntity<List<ProgressDto>> getProgressesByTaskId(@PathVariable long taskId) {
        log.info("Progresses by task id: " + taskId);
        return ok(progressService.getProgressesByTaskId(taskId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') " +
            "or hasAuthority('ROLE_USER') and @userServiceImpl.isUsersId(#userId)")
    @GetMapping("/by_user/{userId}")
    public ResponseEntity<List<ProgressDto>> getProgressesByUserId(@PathVariable long userId) {
        log.info("get progress by user ID: " + userId);
        return ok(progressService.getProgressesByUserId(userId));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/solution_view/{progressId}")
    public ResponseEntity<TaskAndSolutionDto> solutionByProgressId(@PathVariable long progressId) {
        log.info("get solution by progress ID: " + progressId);
        return ok(progressService.solutionByProgressId(progressId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') " +
            "or hasAuthority('ROLE_USER') and @userServiceImpl.isUsersId(#dto.userId)" +
            " and @userServiceImpl.isUserHaveProgress(#dto.taskId)")
    @PostMapping("/solution_view")
    public ResponseEntity<String> progressSolutionByUserIdAndTaskId(@Valid @RequestBody UserIdAndTaskIdDto dto) {
        log.info("get progress by task id and user id : " + dto);
        ProgressDto progressDto = progressService.getProgressByUserIdAndTaskId(dto);
        if (progressDto == null)
            return ok("");
        return ok(progressDto.getSolution());
    }

    @PreAuthorize("hasAuthority('ROLE_USER') and @userServiceImpl.isUsersId(#dto.userId)" +
            " and @userServiceImpl.isUserHaveProgress(#dto.taskId)")
    @PostMapping("/save")
    public ResponseEntity<ProgressDto> save(@Valid @RequestBody SaveProgressDto dto) {
        log.info("save progress " + dto);
        return ok(progressService.saveByUserIdSolutionAndTaskId(dto));
    }

    @PreAuthorize("hasAuthority('ROLE_USER') and @userServiceImpl.isUsersId(#dto.userId)" +
            " and @userServiceImpl.isUserHaveProgress(#dto.taskId)")
    @PutMapping("/update")
    public ResponseEntity<ProgressDto> update(@Valid @RequestBody SaveProgressDto dto) {
        log.info("update progress " + dto);
        return ok(progressService.updateByUserIdSolutionAndTaskId(dto));
    }

}
