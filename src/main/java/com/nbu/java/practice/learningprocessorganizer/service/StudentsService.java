package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dto.CreateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.StudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.UpdateStudentDTO;

import java.util.Collection;

public interface StudentsService {

    StudentDTO getStudent(long studentId);

    Collection<StudentDTO> getAllStudents();

    void createStudent(CreateStudentDTO createStudent);

    void updateStudent(long id, UpdateStudentDTO updateStudent);

}
