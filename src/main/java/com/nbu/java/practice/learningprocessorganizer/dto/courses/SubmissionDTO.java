package com.nbu.java.practice.learningprocessorganizer.dto.courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDTO {

    private double grade;
    private String student;
    private String fn;
}
