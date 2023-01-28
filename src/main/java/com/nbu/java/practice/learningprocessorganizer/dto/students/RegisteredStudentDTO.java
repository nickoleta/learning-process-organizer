package com.nbu.java.practice.learningprocessorganizer.dto.students;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredStudentDTO {

    private long id;
    private String name;
    private String fn;
    private boolean isRegistered;
}
