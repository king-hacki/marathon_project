package com.softserve.marathon.dto.progress;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressDto {

    private Long id;

    @NotNull
    private Long taskId;
    
    @NotEmpty(message = "User email can't be empty")
    private String userMail;

    private String solution;

}
