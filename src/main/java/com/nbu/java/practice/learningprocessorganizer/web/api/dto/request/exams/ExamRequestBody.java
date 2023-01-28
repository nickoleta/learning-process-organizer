package com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams;

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
public class ExamRequestBody {

    @NotNull
    @FutureOrPresent
    private LocalDate openFrom;

    @NotNull
    @FutureOrPresent(message = "End date must be in the future")
    private LocalDate openTo;
}
