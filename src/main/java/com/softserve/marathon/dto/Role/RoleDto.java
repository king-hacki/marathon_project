package com.softserve.marathon.dto.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    @NotEmpty(message = "Role name can't be empty")
    private String name;
    private Long id;

}
