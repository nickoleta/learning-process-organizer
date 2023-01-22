package com.nbu.java.practice.learningprocessorganizer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private String fn;
    private String name;
    private Set<CourseDTO> courses;
}
