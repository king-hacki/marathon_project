package com.softserve.marathon.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {

    @NotBlank
    @Email(message = "invalid email")
    private String email;

    @NotBlank
    private String password;
}
