package com.nbu.java.practice.learningprocessorganizer.web.view.model.questions;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuestionViewModel {

    private long id;

    @NotNull
    private QuestionType questionType;

    @NotBlank
    private String question;

    private String answers;

    private String correctAnswer;

}
