package com.nbu.java.practice.learningprocessorganizer.controller.dto.request.students;

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
public class CreateStudentRequestBody {

    @NotBlank
    @Size(max = 30, message = "The name cannot be longer than 30 characters")
    private String name;

    @NotBlank
    private String fn;
}
