package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Lecturer;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.Student;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.UserIdentity;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.RolesRepository;
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

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

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
        usersRepository.save(convertToUserEntity(user, UserRole.ADMIN_ROLE.getRoleName()));
    }

    @Override
    public void createUserAsLecturer(CreateLecturerViewModel lecturer) {
        if (usersRepository.findByUsername(lecturer.getUsername()) != null) {
            throw new UserAlreadyExistsException(lecturer.getUsername());
        }
        final var userEntity = convertToUserEntity(lecturer, UserRole.LECTURER_ROLE.getRoleName());
        userEntity.setLecturer(modelMapper.map(lecturer, Lecturer.class));
        usersRepository.save(userEntity);
    }

    @Override
    public void createUserAsStudent(CreateStudentViewModel student) {
        if (usersRepository.findByUsername(student.getUsername()) != null) {
            throw new UserAlreadyExistsException(student.getUsername());
        }
        final var userEntity = convertToUserEntity(student, UserRole.STUDENT_ROLE.getRoleName());
        userEntity.setStudent(modelMapper.map(student, Student.class));
        usersRepository.save(userEntity);
    }

    private UserIdentity convertToUserEntity(CreateUserViewModel user, String userRoleName) {
        final var encodedPassword = passwordEncoder.encode(user.getPassword());
        final var role = rolesRepository.getByAuthority(userRoleName);
        return new UserIdentity(user.getUsername(), encodedPassword, true, true, true, true, role);
    }
}
