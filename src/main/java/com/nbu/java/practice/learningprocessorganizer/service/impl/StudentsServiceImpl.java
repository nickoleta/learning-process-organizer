package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.repository.StudentsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.UsersRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.LecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.students.StudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.students.UpdateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
import com.nbu.java.practice.learningprocessorganizer.service.StudentsService;
import com.nbu.java.practice.learningprocessorganizer.service.UsersService;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.students.CreateStudentViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;
    private final UsersRepository usersRepository;
    private final UsersService usersService;
    private final ModelMapper modelMapper;

    @Override
    public StudentDTO getStudent(long studentId) {
        final var student = studentsRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return modelMapper.map(student.get(), StudentDTO.class);
    }

    @Override
    public Collection<StudentDTO> getAllStudents() {
        return modelMapper.map(studentsRepository.findAll(), new TypeToken<Collection<StudentDTO>>() {
        }.getType());
    }

    @Override
    public Page<StudentDTO> getPageOfStudents(Pageable pageable) {
        final var students = studentsRepository.findAll(pageable);
        return modelMapper.map(students, new TypeToken<Page<LecturerDTO>>() {
        }.getType());
    }

    @Override
    public void createStudent(CreateStudentViewModel student) {
        if (student == null) {
            return;
        }
        usersService.createUserAsStudent(student);
    }

    @Override
    public void updateStudent(long id, UpdateStudentDTO updateStudent) {
        final var studentOpt = studentsRepository.findById(id);
        if (studentOpt.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        final var student = studentOpt.get();
        student.setName(updateStudent.getName());
        studentsRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        usersRepository.deleteByStudentId(id);
    }
}
