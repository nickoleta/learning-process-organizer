package com.nbu.java.practice.learningprocessorganizer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeachersController {

    @GetMapping
    public ResponseEntity<Void> getTeachers() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
