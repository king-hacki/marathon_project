package com.softserve.marathon.security.controller;

import com.softserve.marathon.security.dto.SignInDto;
import com.softserve.marathon.security.dto.SuccessSignInDto;
import com.softserve.marathon.security.service.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/security")
@Slf4j
@AllArgsConstructor
public class SecurityController {

    private SecurityService securityService;

    @PostMapping("/signIn")
    public ResponseEntity<SuccessSignInDto> signIn(@Valid @RequestBody SignInDto dto) {
        log.info("sign in");
        return ok(securityService.signIn(dto));
    }

}
