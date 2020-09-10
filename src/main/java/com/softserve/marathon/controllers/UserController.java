package com.softserve.marathon.controllers;

import com.softserve.marathon.dto.user.RegistrationDto;
import com.softserve.marathon.dto.user.UserDto;
import com.softserve.marathon.mapper.UserDtoMapper;
import com.softserve.marathon.model.User;
import com.softserve.marathon.services.UserService;
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
@RequestMapping("/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private UserDtoMapper userDtoMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')" +
            " or hasAuthority('ROLE_USER') and @userServiceImpl.isUsersEmail(#userMail)")
    @GetMapping("/{userMail}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String userMail) {
        log.info("get user by mail: " + userMail);
        return ok(userDtoMapper.convertToDto(userService.getUserByMail(userMail)));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("get all users");
        return ok(userService.getAll());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/marathon/{marathonId}")
    public ResponseEntity<List<UserDto>> getUsersByMarathonId(@PathVariable long marathonId) {
        log.info("get users by marathon id: " + marathonId);
        return ok(userService.getUsersByMarathonId(marathonId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userId) {
        log.info("delete user with id: " + userId);
        userService.deleteById(userId);
        return ok().build();
    }
}
