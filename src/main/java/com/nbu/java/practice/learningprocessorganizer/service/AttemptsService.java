package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Attempt;
import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams.ResultRequestBody;

import java.util.List;
import java.util.Map;

public interface AttemptsService {

    Attempt getAttempt(long id);

    Attempt makeAttempt(long studentId, long examId);

    void addResultsToAttempt(Long attemptId, List<ResultRequestBody> results);

    void addResultsToAttempt(Long attemptId, Map<Long, String> results);

    void updateAttempt(Long attemptId, Attempt attempt);
}
