package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.CreateLecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.LecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.UpdateLecturerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface LecturersService {

    LecturerDTO getLecturer(long studentId);

    Collection<LecturerDTO> getAllLecturers();

    Page<LecturerDTO> getPageOfLecturers(Pageable pageable);

    void createLecturer(CreateLecturerDTO createLecturer);

    void updateLecturer(long id, UpdateLecturerDTO updateLecturer);

    void deleteLecturer(long id);
}
