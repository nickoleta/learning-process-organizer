package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.repository.WeeklyActivityRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.activity.WeeklyActivityDTO;
import com.nbu.java.practice.learningprocessorganizer.service.ActivitiesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ActivitiesServiceImpl implements ActivitiesService {

    private final WeeklyActivityRepository weeklyActivityRepository;
    private final ModelMapper modelMapper;

    @Override
    public void deleteActivity(long id) {
        weeklyActivityRepository.deleteById(id);
    }

    @Override
    public Collection<WeeklyActivityDTO> getActivitiesByCourseId(long courseId) {
        return modelMapper.map(weeklyActivityRepository.findAllByCourseId(courseId), new TypeToken<Collection<WeeklyActivityDTO>>() {
        }.getType());
    }
}
