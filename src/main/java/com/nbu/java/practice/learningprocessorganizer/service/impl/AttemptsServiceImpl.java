package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Attempt;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.Result;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.AttemptsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.ExamsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.QuestionsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.ResultsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.StudentsRepository;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
import com.nbu.java.practice.learningprocessorganizer.service.AttemptsService;
import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams.ResultRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttemptsServiceImpl implements AttemptsService {

    private final AttemptsRepository attemptsRepository;
    private final StudentsRepository studentsRepository;
    private final ExamsRepository examsRepository;
    private final QuestionsRepository questionsRepository;
    private final ResultsRepository resultsRepository;

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

    @Override
    public void addResultsToAttempt(Long attemptId, List<ResultRequestBody> results) {
        final var attemptOpt = attemptsRepository.findById(attemptId);
        if(attemptOpt.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        final var attempt = attemptOpt.get();
        final var resultEntities = results.stream()
                .map(result -> {
                    final var resultEntity = new Result();
                    resultEntity.setQuestion(questionsRepository.findById(result.getQuestionId()).get());
                    resultEntity.setAttempt(attempt);
                    resultEntity.setGivenAnswer(result.getGivenAnswer());
                    return resultEntity;
                }).collect(Collectors.toList());
        resultsRepository.saveAll(resultEntities);
    }

}
