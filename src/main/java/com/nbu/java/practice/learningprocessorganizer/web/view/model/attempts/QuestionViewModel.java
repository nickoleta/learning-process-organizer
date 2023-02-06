package com.nbu.java.practice.learningprocessorganizer.web.view.model.attempts;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class QuestionViewModel {

    private long id;
    private String question;
    private QuestionType questionType;
    private List<AnswerViewModel> answers;

}
