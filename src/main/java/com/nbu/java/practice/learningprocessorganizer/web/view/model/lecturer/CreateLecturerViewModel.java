package com.nbu.java.practice.learningprocessorganizer.web.view.model.lecturer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class CreateLecturerViewModel {

    @NotBlank
    @Size(max = 25)
    private String name;

    @NotNull
    @NotEmpty
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "A strong password contains at least 8 characters, a lower case letter, an upper case letter, a digit and a special character")
    private String password;
}
