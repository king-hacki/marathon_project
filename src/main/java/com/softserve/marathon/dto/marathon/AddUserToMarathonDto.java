package com.softserve.marathon.dto.marathon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserToMarathonDto {

    @NotNull
    private Long marathonId;

    @NotEmpty
    private String email;

}
