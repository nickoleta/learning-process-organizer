package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.StudyMaterial;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.StudyMaterialsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.WeeklyActivityRepository;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundMsgConstants;
import com.nbu.java.practice.learningprocessorganizer.service.StudyMaterialsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class StudyMaterialsServiceImpl implements StudyMaterialsService {

    private final StudyMaterialsRepository studyMaterialsRepository;
    private final WeeklyActivityRepository weeklyActivityRepository;

    @Override
    public void uploadFile(MultipartFile file, long weeklyActivityId) throws IOException {
        final var weeklyActivityOpt = weeklyActivityRepository.findById(weeklyActivityId);
        if (weeklyActivityOpt.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundMsgConstants.ACTIVITY_DOES_NOT_EXIST);
        }
        if (file == null || file.isEmpty() || file.getOriginalFilename() == null) {
            return;
        }
        final var fileName = StringUtils.cleanPath(file.getOriginalFilename());
        studyMaterialsRepository.save(new StudyMaterial(fileName, file.getContentType(), file.getBytes(), weeklyActivityOpt.get()));
    }

    @Override
    public StudyMaterial getFile(long id) {
        final var file = studyMaterialsRepository.findById(id);
        if (file.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundMsgConstants.FILE_DOES_NOT_EXIST);
        }
        return file.get();
    }

    @Override
    public Stream<StudyMaterial> getAllFiles() {
        return studyMaterialsRepository.findAll().stream();
    }
}
