package com.nbu.java.practice.learningprocessorganizer.web.api;

import com.nbu.java.practice.learningprocessorganizer.service.UsersService;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.users.CreateUserViewModel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersApiController {

    private final UsersService userService;

    @PostMapping
    public ResponseEntity<Void> createAdmin(@RequestBody @Valid final CreateUserViewModel user) {
        userService.createUserAsAdmin(user);
        return ResponseEntity.ok().build();
    }
}
