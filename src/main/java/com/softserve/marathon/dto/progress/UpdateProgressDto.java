package com.softserve.marathon.dto.progress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProgressDto {

    @NotEmpty
    private String solution;

    @NotNull
    private Long userId;

    @NotNull
    private Long taskId;

    @NotNull
    private Long progressId;

}
