package com.softserve.marathon.controllers;

import com.softserve.marathon.dto.user.UserDto;
import com.softserve.marathon.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    @GetMapping("/{userMail}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String userMail) {
        log.info("get user by mail: " + userMail);
        return ok(modelMapper.map(userService.getUserByMail(userMail), UserDto.class));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("get all users");
        return ok(userService.getAll());
    }

    @GetMapping("/marathon/{marathonId}")
    public ResponseEntity<List<UserDto>> getUsersByMarathonId(@PathVariable long marathonId) {
        log.info("get users by marathon id: " + marathonId);
        return ok(userService.getUsersByMarathonId(marathonId));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userId) {
        log.info("delete user with id: " + userId);
        userService.deleteById(userId);
        return ok().build();
    }
}
