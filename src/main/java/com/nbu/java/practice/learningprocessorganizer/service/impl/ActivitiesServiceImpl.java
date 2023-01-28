package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Exam;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.ExamsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.WeeklyActivityRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.activity.ExamDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.activity.WeeklyActivityDTO;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
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
    private final ExamsRepository examsRepository;
    private final ModelMapper modelMapper;

    @Override
    public void deleteActivity(long id) {
        weeklyActivityRepository.deleteById(id);
    }

    @Override
    public void addExamToActivity(long activityId, ExamDTO exam) {
        final var activityOpt = weeklyActivityRepository.findById(activityId);
        if (activityOpt.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.ACTIVITY_DOES_NOT_EXIST, activityId);
        }

        final var activity = activityOpt.get();
        final var examEntity = new Exam();
        examEntity.setOpenFrom(exam.getOpenFrom());
        examEntity.setOpenTo(exam.getOpenTo());
        examEntity.setWeeklyActivity(activity);
        examsRepository.save(examEntity);
    }

    @Override
    public Collection<WeeklyActivityDTO> getActivitiesByCourseId(long courseId) {
        return modelMapper.map(weeklyActivityRepository.findAllByCourseId(courseId), new TypeToken<Collection<WeeklyActivityDTO>>() {
        }.getType());
    }
}
