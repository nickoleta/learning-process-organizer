package com.nbu.java.practice.learningprocessorganizer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    private static final String ERROR_MESSAGE = "User with username %s already exists!";

    public UserAlreadyExistsException(String username) {
        super(String.format(ERROR_MESSAGE, username));
    }
}
