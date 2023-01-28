package com.nbu.java.practice.learningprocessorganizer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public static final String COURSE_DOES_NOT_EXIST = "Course with id %s does not exist!";
    public static final String STUDENT_DOES_NOT_EXIST = "Student with id %s does not exist!";
    public static final String LECTURER_DOES_NOT_EXIST = "Lecturer with id %s does not exist!";
    public static final String FILE_DOES_NOT_EXIST = "File with id %s does not exist!";
    public static final String ACTIVITY_DOES_NOT_EXIST = "Weekly activity with id %s does not exist!";
    public static final String EXAM_DOES_NOT_EXIST = "Exam with id %s does not exist!";

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message, long id) {
        super(String.format(message, id));
    }
}
