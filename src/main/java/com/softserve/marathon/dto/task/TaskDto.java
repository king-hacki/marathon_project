package com.softserve.marathon.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    @NotEmpty(message = "Task title must not be empty")
    private String title;

    @NotEmpty(message = "Task description must not be empty")
    private String taskDescription;

    @NotNull
    @NotEmpty
    private String statusName;

    @NotNull
    private Long sprintId;

}
