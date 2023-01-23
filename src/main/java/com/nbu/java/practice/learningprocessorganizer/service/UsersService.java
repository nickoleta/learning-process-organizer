package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.web.view.model.lecturer.CreateLecturerViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.students.CreateStudentViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.users.CreateUserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

    void createUserAsAdmin(CreateUserViewModel user);

    void createUserAsLecturer(CreateLecturerViewModel user);

    void createUserAsStudent(CreateStudentViewModel user);

}
