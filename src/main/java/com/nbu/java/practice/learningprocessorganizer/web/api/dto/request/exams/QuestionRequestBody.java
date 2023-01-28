package com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.QuestionType;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.AnswerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestBody {

    @NotBlank
    private String question;

    @NotNull
    private QuestionType questionType;

    private Set<AnswerDTO> answers;
}
