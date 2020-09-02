package com.softserve.marathon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarathonDto {

    @NotNull
    @Min(value = 1, message = "Marathon id must be a positive number")
    private Long id;

    @NotEmpty(message = "Marathon text must not be null")
    private String title;
}
