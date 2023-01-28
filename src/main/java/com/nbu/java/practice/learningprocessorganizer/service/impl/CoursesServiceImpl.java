package com.nbu.java.practice.learningprocessorganizer.service.impl;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Course;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.WeeklyActivity;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.CoursesRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.LecturersRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.StudentsRepository;
import com.nbu.java.practice.learningprocessorganizer.dao.repository.WeeklyActivityRepository;
import com.nbu.java.practice.learningprocessorganizer.dto.activity.WeeklyActivityDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.CourseDTO;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
import com.nbu.java.practice.learningprocessorganizer.service.CoursesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;
    private final StudentsRepository studentsRepository;
    private final LecturersRepository lecturersRepository;
    private final WeeklyActivityRepository weeklyActivityRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseDTO getCourse(long courseId) {
        final var course = coursesRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.COURSE_DOES_NOT_EXIST, courseId);
        }
        return modelMapper.map(course.get(), CourseDTO.class);
    }

    @Override
    public Collection<CourseDTO> getAllCourses() {
        return modelMapper.map(coursesRepository.findAll(), new TypeToken<Collection<CourseDTO>>() {
        }.getType());
    }

    @Override
    public Page<CourseDTO> getPageOfCourses(Pageable pageable) {
        return modelMapper.map(coursesRepository.findAll(pageable), new TypeToken<Page<CourseDTO>>() {
        }.getType());
    }

    @Override
    public Page<CourseDTO> getPageOfCoursesByLecturerId(long lecturerId, Pageable pageable) {
        return modelMapper.map(coursesRepository.findAllByLecturer_id(lecturerId, pageable), new TypeToken<Page<CourseDTO>>() {
        }.getType());
    }

    @Override
    public Page<CourseDTO> getPageOfCoursesByStudentId(long studentId, Pageable pageable) {
        return modelMapper.map(coursesRepository.findByStudents_Id(studentId, pageable), new TypeToken<Page<CourseDTO>>() {
        }.getType());
    }

    @Override
    public void addStudentToCourse(long courseId, long studentId) {
        final var studentOpt = studentsRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.STUDENT_DOES_NOT_EXIST, courseId);
        }
        final var courseOpt = coursesRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.COURSE_DOES_NOT_EXIST, courseId);
        }
        final var course = courseOpt.get();
        course.addStudent(studentOpt.get());
        coursesRepository.save(course);
    }

    @Override
    public void removeStudentToCourse(long courseId, long studentId) {
        final var studentOpt = studentsRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.STUDENT_DOES_NOT_EXIST, courseId);
        }
        final var courseOpt = coursesRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.COURSE_DOES_NOT_EXIST, courseId);
        }
        final var course = courseOpt.get();
        course.removeStudent(studentOpt.get());
        coursesRepository.save(course);
    }

    @Override
    public void addActivityToACourse(long courseId, WeeklyActivityDTO weeklyActivity) {
        final var courseOpt = coursesRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.COURSE_DOES_NOT_EXIST, courseId);
        }

        final var activity = modelMapper.map(weeklyActivity, WeeklyActivity.class);
        activity.setCourse(courseOpt.get());
        weeklyActivityRepository.save(activity);
    }

    @Override
    public void createCourse(long lecturerId, CourseDTO courseDTO) {
        if (courseDTO == null) {
            return;
        }
        final var lecturer = lecturersRepository.findById(lecturerId);
        if (lecturer.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.LECTURER_DOES_NOT_EXIST, lecturerId);
        }
        final var course = modelMapper.map(courseDTO, Course.class);
        course.setLecturer(lecturer.get());
        coursesRepository.save(course);
    }

}
