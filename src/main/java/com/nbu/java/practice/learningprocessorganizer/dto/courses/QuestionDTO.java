package com.nbu.java.practice.learningprocessorganizer.dto.courses;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private String question;
    private QuestionType questionType;
    private Set<AnswerDTO> answers;
}
