package com.nbu.java.practice.learningprocessorganizer.web.view.model.activities;

import com.nbu.java.practice.learningprocessorganizer.web.view.model.attempts.QuestionViewModel;
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
public class ExamViewModel {

    private Long id;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openFrom = LocalDate.now();

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openTo = LocalDate.now();

    private List<QuestionViewModel> questions;

    private boolean isPublished;

}
