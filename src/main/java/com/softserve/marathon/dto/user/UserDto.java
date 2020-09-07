package com.softserve.marathon.dto.user;

import com.softserve.marathon.dto.Role.RoleDto;
import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.dto.progress.ProgressDto;
import com.softserve.marathon.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "First name can't be empty")
    private String firstName;

    @NotEmpty(message = "Last name can't be empty")
    private String lastName;

    @Valid
    private Set<ProgressDto> progressesDto = new LinkedHashSet<>();

    @Valid
    private Set<MarathonDto> marathonsDto = new LinkedHashSet<>();

    @Valid
    private Set<RoleDto> roleDto = new LinkedHashSet<>();

}
