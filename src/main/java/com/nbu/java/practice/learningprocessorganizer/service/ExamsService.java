package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dto.courses.ExamDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.QuestionDTO;

public interface ExamsService {

    ExamDTO getExam(long examId);

    void save(long activityId, ExamDTO examDTO);

    void addQuestionToExam(long examId, QuestionDTO question);

    void publishExam(long examId);
}
