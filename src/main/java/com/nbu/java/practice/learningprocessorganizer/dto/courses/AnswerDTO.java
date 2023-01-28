package com.nbu.java.practice.learningprocessorganizer.dto.courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private boolean isCorrect;
    private String value;

    public AnswerDTO(String value) {
        this.value = value;
    }
}
