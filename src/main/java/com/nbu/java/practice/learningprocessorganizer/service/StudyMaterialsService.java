package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.StudyMaterial;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface StudyMaterialsService {

    void uploadFile(MultipartFile multipartFile, long weeklyActivityId) throws IOException;

    StudyMaterial getFile(long id);

    Stream<StudyMaterial> getAllFiles();
}
