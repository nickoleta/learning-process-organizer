package com.nbu.java.practice.learningprocessorganizer.web.view.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserViewModel {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

}
