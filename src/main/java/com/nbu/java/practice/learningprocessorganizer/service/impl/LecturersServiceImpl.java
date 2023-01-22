package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Lecturer;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.LecturersRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.CreateLecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.LecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.UpdateLecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
import com.nbu.java.practice.learningprocessorganizer.service.LecturersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class LecturersServiceImpl implements LecturersService {

    private final LecturersRepository lecturersRepository;
    private final ModelMapper modelMapper;

    @Override
    public LecturerDTO getLecturer(long studentId) {
        final var lecturer = lecturersRepository.findById(studentId);
        if (lecturer.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return modelMapper.map(lecturer.get(), LecturerDTO.class);
    }

    @Override
    public Collection<LecturerDTO> getAllLecturers() {
        return modelMapper.map(lecturersRepository.findAll(), new TypeToken<Collection<LecturerDTO>>() {
        }.getType());
    }

    @Override
    public void createLecturer(CreateLecturerDTO lecturer) {
        if (lecturer == null) {
            return;
        }
        lecturersRepository.save(modelMapper.map(lecturer, Lecturer.class));
    }

    @Override
    public void updateLecturer(long id, UpdateLecturerDTO updateLecturer) {
        final var lecturerOpt = lecturersRepository.findById(id);
        if (lecturerOpt.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        final var lecturer = lecturerOpt.get();
        lecturer.setName(updateLecturer.getName());
        lecturersRepository.save(lecturer);
    }

    @Override
    public void deleteLecturer(long id) {
        final var lecturer = lecturersRepository.findById(id);
        if (lecturer.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        lecturersRepository.delete(lecturer.get());
    }
}
