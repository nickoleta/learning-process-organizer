package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Attempt;
import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams.ResultRequestBody;

import java.util.List;

public interface AttemptsService {

    Attempt getAttempt(long id);

    void makeAttempt(long studentId, long examId);

    void addResultsToAttempt(Long attemptId, List<ResultRequestBody> results);

    void updateAttempt(Long attemptId, Attempt attempt);
}
