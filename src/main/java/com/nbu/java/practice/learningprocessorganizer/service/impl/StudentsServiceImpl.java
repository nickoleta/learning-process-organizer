package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Student;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.StudentsRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.CreateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.StudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.UpdateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFound;
import com.nbu.java.practice.learningprocessorganizer.service.StudentsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentDTO getStudent(long studentId) {
        final var student = studentsRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new ResourceNotFound();
        }
        return modelMapper.map(student.get(), StudentDTO.class);
    }

    @Override
    public Collection<StudentDTO> getAllStudents() {
        return modelMapper.map(studentsRepository.findAll(), new TypeToken<Collection<StudentDTO>>() {
        }.getType());
    }

    @Override
    public void createStudent(CreateStudentDTO student) {
        if (student == null) {
            return;
        }
        studentsRepository.save(modelMapper.map(student, Student.class));
    }

    @Override
    public void updateStudent(long id, UpdateStudentDTO updateStudent) {
        final var studentOpt = studentsRepository.findById(id);
        if (studentOpt.isEmpty()) {
            throw new ResourceNotFound();
        }
        final var student = studentOpt.get();
        student.setName(updateStudent.getName());
        studentsRepository.save(student);
    }
}
