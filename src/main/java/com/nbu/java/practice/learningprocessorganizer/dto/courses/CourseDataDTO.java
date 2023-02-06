package com.nbu.java.practice.learningprocessorganizer.dto.courses;

import com.nbu.java.practice.learningprocessorganizer.dto.activity.WeeklyActivityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class CourseDataDTO {

    private long id;
    private String name;
    private Set<WeeklyActivityDTO> weeklyActivities;
}
