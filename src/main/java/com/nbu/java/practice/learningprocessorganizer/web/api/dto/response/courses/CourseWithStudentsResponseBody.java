package com.nbu.java.practice.learningprocessorganizer.web.api.dto.response.courses;

import com.nbu.java.practice.learningprocessorganizer.dto.students.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Generated
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CourseWithStudentsResponseBody extends CourseResponseBody {

    private Set<StudentDTO> students;
}
