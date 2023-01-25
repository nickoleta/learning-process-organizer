package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.repository.WeeklyActivityRepository;
import com.nbu.java.practice.learningprocessorganizer.service.ActivitiesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActivitiesServiceImpl implements ActivitiesService {

    private final WeeklyActivityRepository weeklyActivityRepository;

    @Override
    public void deleteActivity(long id) {
        weeklyActivityRepository.deleteById(id);
    }
}
