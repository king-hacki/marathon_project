package com.softserve.marathon.controllers;

import com.softserve.marathon.dto.marathon.AddUserToMarathonDto;
import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.User;
import com.softserve.marathon.services.CrudService;
import com.softserve.marathon.services.MarathonService;
import com.softserve.marathon.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/marathon")
@Slf4j
@AllArgsConstructor
public class MarathonController {

    private MarathonService marathonService;
    private UserService userService;
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<MarathonDto>> getAllMarathons() {
        log.info("get all marathons");
        return ok(marathonService.getAll());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') " +
            "or hasAuthority('ROLE_USER') " +
            "and @userServiceImpl.isUserHasMarathon(#userId)")
    @GetMapping("/{userId}")
    public ResponseEntity<List<MarathonDto>> getMarathonByUserId(@PathVariable long userId) {
        log.info("get marathons by user ID: " + userId);
        return ok(marathonService.getByUserId(userId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add_user")
    public ResponseEntity<Boolean> addUserToMarathon(@Valid @RequestBody AddUserToMarathonDto dto) {
        log.info("add user to marathon: " + dto);
        return ok(userService.addUserToMarathon(dto.getEmail(), dto.getMarathonId()));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{marathonId}")
    public ResponseEntity<MarathonDto> delete(@PathVariable long marathonId) {
        log.info("Delete marathon with id: " + marathonId);
        marathonService.deleteById(marathonId);
        return ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<MarathonDto> update(@Valid @RequestBody MarathonDto dto) {
        log.info("update Marathon: " + dto);
        return ok(modelMapper.map(marathonService.update(dto), MarathonDto.class));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<MarathonDto> save(@RequestBody MarathonDto dto) {
        log.info("save Marathon: " + dto);
        return ok(modelMapper.map(marathonService.save(dto), MarathonDto.class));
    }

}
