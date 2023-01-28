package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams.ResultRequestBody;

import java.util.List;

public interface AttemptsService {

    void makeAttempt(long studentId, long examId);

    void addResultsToAttempt(Long attemptId, List<ResultRequestBody> results);
}
