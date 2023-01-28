package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Exam;
import com.nbu.java.practice.learningprocessorganizer.dto.activity.ExamDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.activity.WeeklyActivityDTO;

import java.util.Collection;

public interface ActivitiesService {

    void deleteActivity(long id);

    Exam addExamToActivity(long activityId, ExamDTO exam);

    Collection<WeeklyActivityDTO> getActivitiesByCourseId(long courseId);
}
