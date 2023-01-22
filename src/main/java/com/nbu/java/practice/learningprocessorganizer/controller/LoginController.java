package com.nbu.java.practice.learningprocessorganizer.controller;

import com.nbu.java.practice.learningprocessorganizer.controller.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    @PostMapping
    public ResponseEntity<UserResponse> login(@RequestBody @Valid final UserRequestBody userRequestBody) {
        return ResponseEntity.ok().build();
    }

}
