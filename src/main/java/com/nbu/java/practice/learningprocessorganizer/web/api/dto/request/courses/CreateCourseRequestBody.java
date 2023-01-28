package com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseRequestBody {

    @NotNull(message = "Lecturer ID cannot be null")
    @Positive(message = "Lecturer ID cannot have a negative value")
    private Long lecturerId;

    @NotBlank
    @Size(max = 50, message = "The name cannot be longer than 50 characters")
    private String name;

    private Set<Long> studentIds;
}
