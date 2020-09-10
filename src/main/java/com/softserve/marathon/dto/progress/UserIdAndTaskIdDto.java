package com.softserve.marathon.dto.progress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserIdAndTaskIdDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long taskId;

}
