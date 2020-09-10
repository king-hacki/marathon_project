package com.softserve.marathon.security.controller;

import com.softserve.marathon.dto.user.RegistrationDto;
import com.softserve.marathon.dto.user.UserDto;
import com.softserve.marathon.security.dto.SignInDto;
import com.softserve.marathon.security.dto.SuccessSignInDto;
import com.softserve.marathon.security.service.SecurityService;
import com.softserve.marathon.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/security")
@Slf4j
@AllArgsConstructor
public class SecurityController {

    private SecurityService securityService;
    private UserService userService;


    @PreAuthorize("permitAll()")
    @PostMapping("/signIn")
    public ResponseEntity<SuccessSignInDto> signIn(@Valid @RequestBody SignInDto dto) {
        log.info("sign in");
        return ok(securityService.signIn(dto));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResponseEntity<UserDto> registration(@Valid @RequestBody RegistrationDto dto) {
        log.info("registration " + dto);
        return ok(userService.registration(dto));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/load")
    public ResponseEntity<UserDto> loadUser(@RequestHeader("authorization") String jwtToken) {
        log.info("load user");
        return ok(securityService.loadUser(jwtToken));
    }

}
