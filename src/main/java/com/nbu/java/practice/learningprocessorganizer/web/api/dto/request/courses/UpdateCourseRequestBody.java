package com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseRequestBody {

    @NotBlank
    @Size(max = 50, message = "The name cannot be longer than 50 characters")
    private String name;
}
