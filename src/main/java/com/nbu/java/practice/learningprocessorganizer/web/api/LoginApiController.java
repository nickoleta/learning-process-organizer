package com.nbu.java.practice.learningprocessorganizer.web.api;

import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.UserRequestBody;
import com.nbu.java.practice.learningprocessorganizer.web.api.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class LoginApiController {

    @PostMapping
    public ResponseEntity<UserResponse> login(@RequestBody @Valid final UserRequestBody userRequestBody) {
        return ResponseEntity.ok().build();
    }

}
