package com.softserve.marathon.security.service;


import com.softserve.marathon.dto.user.UserDto;
import com.softserve.marathon.security.dto.SignInDto;
import com.softserve.marathon.security.dto.SuccessSignInDto;

public interface SecurityService {
    SuccessSignInDto signIn(SignInDto dto);
    UserDto loadUser(String token);
}
