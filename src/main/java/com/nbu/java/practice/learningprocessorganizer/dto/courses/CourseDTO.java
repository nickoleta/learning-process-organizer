package com.nbu.java.practice.learningprocessorganizer.dto.courses;

import com.nbu.java.practice.learningprocessorganizer.dto.activity.WeeklyActivityDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.LecturerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private long id;
    private String name;
    private LecturerDTO lecturer;
    private Set<WeeklyActivityDTO> weeklyActivities;
}
