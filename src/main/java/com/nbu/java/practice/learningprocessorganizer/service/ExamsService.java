package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dto.activity.ExamDTO;

public interface ExamsService {

    void save(long activityId, ExamDTO examDTO);
}
