package com.softserve.marathon.security.service.impl;

import com.softserve.marathon.exceptions.BadEmailOrPasswordException;
import com.softserve.marathon.security.dto.SignInDto;
import com.softserve.marathon.security.dto.SuccessSignInDto;
import com.softserve.marathon.security.jwt.JwtTool;
import com.softserve.marathon.security.service.SecurityService;
import com.softserve.marathon.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private JwtTool jwtTool;

    @Override
    public SuccessSignInDto signIn(SignInDto dto) {
        UserDetails userDetails = userService.loadUserByUsername(dto.getEmail());
        if (passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())) {
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            String accessToken = jwtTool.createToken(userDetails.getUsername(), roles);
            return SuccessSignInDto.builder()
                    .accessToken(accessToken)
                    .username(userDetails.getUsername())
                    .build();
        }
        throw new BadEmailOrPasswordException();
    }
}
