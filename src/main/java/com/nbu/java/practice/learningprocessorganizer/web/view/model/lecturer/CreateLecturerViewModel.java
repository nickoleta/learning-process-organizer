package com.nbu.java.practice.learningprocessorganizer.web.view.model.lecturer;

import com.nbu.java.practice.learningprocessorganizer.web.view.model.users.CreateUserViewModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Generated
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateLecturerViewModel extends CreateUserViewModel {

    @NotBlank
    @Size(max = 25)
    private String name;
}
