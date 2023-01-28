package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dto.activity.ExamDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.QuestionDTO;

public interface ExamsService {

    void save(long activityId, ExamDTO examDTO);

    void addQuestionToExam(long examId, QuestionDTO question);
}
