package com.nbu.java.practice.learningprocessorganizer.web.view.model.students;

import com.nbu.java.practice.learningprocessorganizer.web.view.model.users.CreateUserViewModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Generated
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentViewModel extends CreateUserViewModel {

    @NotBlank
    @Size(max = 25)
    private String name;

    @NotBlank
    @Size(min = 6, max = 6, message = "A valid faculty number contains 6 numbers")
    private String fn;
}
