package com.nbu.java.practice.learningprocessorganizer.dto.courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {

    private long id;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openFrom;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openTo;

    private boolean isPublished;

    private List<QuestionDTO> questions;
}
