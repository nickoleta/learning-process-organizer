package com.nbu.java.practice.learningprocessorganizer.dto.lecturers;

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
public class LecturerDTO {

    private long id;
    private String name;
    private Set<CourseDTO> courses;
}
