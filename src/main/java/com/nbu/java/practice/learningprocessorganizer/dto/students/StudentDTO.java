package com.nbu.java.practice.learningprocessorganizer.dto.students;

import com.nbu.java.practice.learningprocessorganizer.dto.courses.CourseDTO;
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

    private long id;
    private String fn;
    private String name;
    private Set<CourseDTO> courses;
}
