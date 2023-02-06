package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Attempt;
import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams.ResultRequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AttemptsService {

    Attempt getAttempt(long id);

    Optional<Attempt> getAttempt(long studentId, long examId);

    Attempt makeAttempt(long studentId, long examId);

    void addResultsToAttempt(Long attemptId, List<ResultRequestBody> results);

    void addResultsToAttempt(Long attemptId, Map<Long, String> results);

    void updateAttempt(Long attemptId, Attempt attempt);
}
