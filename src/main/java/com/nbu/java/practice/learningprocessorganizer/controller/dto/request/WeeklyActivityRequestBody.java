package com.nbu.java.practice.learningprocessorganizer.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyActivityRequestBody {

    @NotNull
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent(message = "End date must be in the future")
    private LocalDate endDate;
}
