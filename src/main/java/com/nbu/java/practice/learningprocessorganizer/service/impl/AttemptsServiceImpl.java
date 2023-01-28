package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Attempt;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.AttemptsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.ExamsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.StudentsRepository;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
import com.nbu.java.practice.learningprocessorganizer.service.AttemptsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttemptsServiceImpl implements AttemptsService {

    private final AttemptsRepository attemptsRepository;
    private final StudentsRepository studentsRepository;
    private final ExamsRepository examsRepository;

    @Override
    public void makeAttempt(long studentId, long examId) {
        final var studentOpt = studentsRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.STUDENT_DOES_NOT_EXIST, studentId);
        }
        final var examOpt = examsRepository.findById(examId);
        if (examOpt.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.EXAM_DOES_NOT_EXIST, studentId);
        }
        attemptsRepository.save(new Attempt(studentOpt.get(), examOpt.get(), List.of()));
    }
}
