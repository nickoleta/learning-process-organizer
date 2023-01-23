package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dto.students.CreateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.students.StudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.students.UpdateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.students.CreateStudentViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface StudentsService {

    StudentDTO getStudent(long studentId);

    Collection<StudentDTO> getAllStudents();

    Page<StudentDTO> getPageOfStudents(Pageable pageable);

    void createStudent(CreateStudentViewModel createStudent);

    void updateStudent(long id, UpdateStudentDTO updateStudent);

    void deleteStudent(long id);

}
