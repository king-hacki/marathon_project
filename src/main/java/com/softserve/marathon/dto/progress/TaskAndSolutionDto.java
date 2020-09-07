package com.softserve.marathon.dto.progress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskAndSolutionDto {

    @NotEmpty
    private String task;
    private String solution;
}
