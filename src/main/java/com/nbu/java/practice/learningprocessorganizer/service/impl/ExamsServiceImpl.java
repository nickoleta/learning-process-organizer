package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Exam;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.ExamsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.WeeklyActivityRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.activity.ExamDTO;
import com.nbu.java.practice.learningprocessorganizer.service.ExamsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExamsServiceImpl implements ExamsService {

    private final WeeklyActivityRepository weeklyActivityRepository;
    private final ExamsRepository examsRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(long activityId, ExamDTO examDTO) {
        examsRepository.save(modelMapper.map(examDTO, Exam.class));
    }
}
