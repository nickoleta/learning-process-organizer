package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dto.activity.WeeklyActivityDTO;

import java.util.Collection;

public interface ActivitiesService {

    void deleteActivity(long id);

    Collection<WeeklyActivityDTO> getActivitiesByCourseId(long courseId);
}
