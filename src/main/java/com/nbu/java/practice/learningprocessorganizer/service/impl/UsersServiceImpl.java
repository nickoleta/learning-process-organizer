package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Lecturer;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.Student;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.UserIdentity;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.LecturersRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.RolesRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.StudentsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.UsersRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.UserRole;
import com.nbu.java.practice.learningprocessorganizer.exceptions.UserAlreadyExistsException;
import com.nbu.java.practice.learningprocessorganizer.service.UsersService;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.lecturer.CreateLecturerViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.students.CreateStudentViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.users.CreateUserViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final LecturersRepository lecturersRepository;
    private final StudentsRepository studentsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public void createUserAsAdmin(CreateUserViewModel user) {
        if (usersRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException(user.getUsername());
        }
        usersRepository.save(convertToUserEntity(user.getUsername(), user.getPassword(), UserRole.ADMIN_ROLE.getRoleName()));
    }

    @Override
    public void createUserAsLecturer(CreateLecturerViewModel lecturer) {
        if (usersRepository.findByUsername(lecturer.getUsername()) != null) {
            throw new UserAlreadyExistsException(lecturer.getUsername());
        }
        final var userIdentity = convertToUserEntity(lecturer.getUsername(), lecturer.getPassword(), UserRole.LECTURER_ROLE.getRoleName());
        final var lecturerEntity = new Lecturer(lecturer.getName(), Set.of(), userIdentity);
        userIdentity.setLecturer(lecturerEntity);
        lecturersRepository.save(lecturerEntity);
    }

    @Override
    public void createUserAsStudent(CreateStudentViewModel student) {
        if (usersRepository.findByUsername(student.getUsername()) != null) {
            throw new UserAlreadyExistsException(student.getUsername());
        }
        final var userIdentity = convertToUserEntity(student.getUsername(), student.getPassword(), UserRole.STUDENT_ROLE.getRoleName());
        final var studentEntity = new Student(student.getFn(), student.getName(), Set.of(), Set.of(), Set.of(), userIdentity);
        userIdentity.setStudent(studentEntity);
        studentsRepository.save(studentEntity);
    }

    private UserIdentity convertToUserEntity(String username, String password, String userRoleName) {
        final var encodedPassword = passwordEncoder.encode(password);
        final var role = rolesRepository.getByAuthority(userRoleName);
        return new UserIdentity(username, encodedPassword, true, true, true, true, role);
    }
}
