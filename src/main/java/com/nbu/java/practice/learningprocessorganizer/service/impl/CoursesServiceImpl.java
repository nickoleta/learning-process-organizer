package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Course;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.CoursesRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.LecturersRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.StudentsRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.CourseDTO;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
import com.nbu.java.practice.learningprocessorganizer.service.CoursesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.nbu.java.practice.learningprocessorganizer.service.impl.ResourceNotFoundMsgConstants.COURSE_DOES_NOT_EXIST;
import static com.nbu.java.practice.learningprocessorganizer.service.impl.ResourceNotFoundMsgConstants.LECTURER_DOES_NOT_EXIST;
import static com.nbu.java.practice.learningprocessorganizer.service.impl.ResourceNotFoundMsgConstants.STUDENT_DOES_NOT_EXIST;

@Service
@AllArgsConstructor
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;
    private final StudentsRepository studentsRepository;
    private final LecturersRepository lecturersRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseDTO getCourse(long courseId) {
        final var course = coursesRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new ResourceNotFoundException(String.format(COURSE_DOES_NOT_EXIST, courseId));
        }
        return modelMapper.map(course.get(), CourseDTO.class);
    }

    @Override
    public Collection<CourseDTO> getAllCourses() {
        return modelMapper.map(coursesRepository.findAll(), new TypeToken<Collection<CourseDTO>>() {
        }.getType());
    }

    @Override
    public Collection<CourseDTO> getAllCoursesByLecturerId(long lecturerId) {
        return modelMapper.map(coursesRepository.findAllByLecturerId(lecturerId), new TypeToken<Collection<CourseDTO>>() {
        }.getType());
    }

    @Override
    public void addStudentToCourse(long courseId, long studentId) {
        final var studentOpt = studentsRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format(STUDENT_DOES_NOT_EXIST, courseId));
        }
        final var courseOpt = coursesRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format(COURSE_DOES_NOT_EXIST, courseId));
        }
        final var course = courseOpt.get();
        course.addStudent(studentOpt.get());
        coursesRepository.save(course);
    }

    @Override
    public void createCourse(long lecturerId, CourseDTO courseDTO) {
        if (courseDTO == null) {
            return;
        }
        final var lecturer = lecturersRepository.findById(lecturerId);
        if (lecturer.isEmpty()) {
            throw new ResourceNotFoundException(String.format(LECTURER_DOES_NOT_EXIST, lecturerId));
        }
        final var course = modelMapper.map(courseDTO, Course.class);
        course.setLecturer(lecturer.get());
        coursesRepository.save(course);
    }

    @Override
    public void updateCourse(long id, CourseDTO courseDTO) {
        final var courseOpt = coursesRepository.findById(id);
        if (courseOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format(COURSE_DOES_NOT_EXIST, id));
        }
        final var course = courseOpt.get();
        course.setName(courseDTO.getName());
        coursesRepository.save(course);
    }

    @Override
    public void deleteCourse(long id) {
        coursesRepository.deleteById(id);
    }
}
