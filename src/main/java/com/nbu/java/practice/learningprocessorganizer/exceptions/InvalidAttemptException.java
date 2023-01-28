package com.nbu.java.practice.learningprocessorganizer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidAttemptException extends RuntimeException {

    public InvalidAttemptException() {
        super("Invalid exam attempt! Please connect your lecturer!");
    }
}
