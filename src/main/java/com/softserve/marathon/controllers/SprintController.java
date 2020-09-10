package com.softserve.marathon.controllers;


import com.softserve.marathon.dto.sprint.SprintDto;
import com.softserve.marathon.mapper.SprintDtoMapper;
import com.softserve.marathon.model.User;
import com.softserve.marathon.services.SprintService;
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
@RequestMapping("/sprint")
@Slf4j
@AllArgsConstructor
public class SprintController {

    private SprintService sprintService;
    private SprintDtoMapper sprintDtoMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')" +
            " or hasAuthority('ROLE_USER') and @userServiceImpl.isUserHasSprint(#marathonId)")
    @GetMapping("/{marathonId}")
    public ResponseEntity<List<SprintDto>> getSprintsByMarathonId(@PathVariable long marathonId) {
        log.info("get all sprints from marathon with id: " + marathonId);
        return ok(sprintService.getSprintsByMarathon(marathonId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{sprintId}")
    public ResponseEntity<SprintDto> delete(@PathVariable long sprintId) {
        log.info("Delete sprint with id: " + sprintId);
        sprintService.deleteById(sprintId);;
        return ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<SprintDto> update(@Valid @RequestBody SprintDto dto) {
        log.info("update Sprint: " + dto);
        return ok(sprintDtoMapper.convertToDto(sprintService.update(dto)));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<SprintDto> save(@RequestBody SprintDto dto) {
        log.info("save Sprint: " + dto);
        return ok(sprintDtoMapper.convertToDto(sprintService.save(dto)));
    }

}
