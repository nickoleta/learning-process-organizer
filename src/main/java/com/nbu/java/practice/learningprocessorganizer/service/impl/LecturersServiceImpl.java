package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.repository.LecturersRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.LecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.UpdateLecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
import com.nbu.java.practice.learningprocessorganizer.service.LecturersService;
import com.nbu.java.practice.learningprocessorganizer.service.UsersService;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.lecturer.CreateLecturerViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class LecturersServiceImpl implements LecturersService {

    private final UsersService usersService;
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
    public Page<LecturerDTO> getPageOfLecturers(Pageable pageable) {
        final var lecturers = lecturersRepository.findAll(pageable);
        return modelMapper.map(lecturers, new TypeToken<Page<LecturerDTO>>() {
        }.getType());
    }

    @Override
    public void createLecturer(CreateLecturerViewModel lecturer) {
        if (lecturer == null) {
            return;
        }
        usersService.createUserAsLecturer(lecturer);
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
        lecturersRepository.deleteById(id);
    }
}
