package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dto.courses.CourseDTO;

import java.util.Collection;

public interface CoursesService {

    CourseDTO getCourse(long courseId);

    Collection<CourseDTO> getAllCourses();

    Collection<CourseDTO> getAllCoursesByLecturerId(long lecturerId);

    void addStudentToCourse(long courseId, long studentId);

    void createCourse(long lecturerId, CourseDTO courseDTO);

    void updateCourse(long id, CourseDTO courseDTO);

    void deleteCourse(long id);
}
