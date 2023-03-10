package com.nbu.java.practice.learningprocessorganizer.web.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestBody {

    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
