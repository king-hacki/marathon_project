package com.softserve.marathon.security.service.impl;

import com.softserve.marathon.dto.user.UserDto;
import com.softserve.marathon.exceptions.BadEmailOrPasswordException;
import com.softserve.marathon.mapper.UserDtoMapper;
import com.softserve.marathon.model.User;
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
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private UserDtoMapper userDtoMapper;
    private JwtTool jwtTool;

    @Override
    public SuccessSignInDto signIn(SignInDto dto) {
        UserDetails userDetails = userService.loadUserByUsername(dto.getEmail());
        User user = userService.getUserByMail(userDetails.getUsername());
        if (passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())) {
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(toList());
            String accessToken = jwtTool.createToken(userDetails.getUsername(), roles);
            return SuccessSignInDto.builder()
                    .accessToken(accessToken)
                    .role(user.getRole().stream()
                            .map(role -> role.getRoleName().name())
                            .collect(toList()))
                    .build();
        }
        throw new BadEmailOrPasswordException();
    }

    public UserDto loadUser(String jwtToken) {
        if (StringUtils.hasText(jwtToken) && jwtToken.startsWith("Bearer "))
            jwtToken = jwtToken.substring(7);
        if (!jwtTool.validateToken(jwtToken)) return null;
        String email = jwtTool.getUsernameFromToken(jwtToken);
        User userEntity = userService.getUserByMail(email);
        return userDtoMapper.convertToDto(userEntity);
    }

}
