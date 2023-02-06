package com.nbu.java.practice.learningprocessorganizer.web.view.model.students;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentViewModel {

    @NotBlank(message = "The name of as student cannot be empty")
    @Size(max = 25)
    private String name;

    @NotBlank
    @Size(min = 6, max = 6, message = "A valid faculty number contains 6 numbers")
    private String fn;

    @NotBlank(message = "A username is required")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "A strong password contains at least 8 characters, a lower case letter, an upper case letter, a digit and a special character")
    private String password;
}
