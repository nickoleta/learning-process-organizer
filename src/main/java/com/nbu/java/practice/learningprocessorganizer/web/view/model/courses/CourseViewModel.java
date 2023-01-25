package com.nbu.java.practice.learningprocessorganizer.web.view.model.courses;

import com.nbu.java.practice.learningprocessorganizer.web.view.model.activities.ActivityViewModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class CourseViewModel {

    @NotBlank
    @Size(max = 30)
    private String name;

    private Set<ActivityViewModel> activities;

}
