package com.nbu.java.practice.learningprocessorganizer.controller.dto.response;

import com.nbu.java.practice.learningprocessorganizer.dto.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String name;
    private String username;
    private UserRole role;
}
