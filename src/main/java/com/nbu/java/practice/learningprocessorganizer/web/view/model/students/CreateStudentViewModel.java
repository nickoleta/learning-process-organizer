package com.nbu.java.practice.learningprocessorganizer.web.view.model.students;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentViewModel {

    @NotBlank
    @Size(max = 25)
    private String name;

    @NotBlank
    @Size(min = 6, max = 6, message = "A valid faculty number contains 6 numbers")
    private String fn;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;
}
