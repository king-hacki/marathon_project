package com.softserve.marathon.dto.marathon;

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
    @NotEmpty(message = "Marathon title must not be empty")
    private String title;
    private Long id;
}
