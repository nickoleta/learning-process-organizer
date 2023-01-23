package com.nbu.java.practice.learningprocessorganizer.service;

import com.nbu.java.practice.learningprocessorganizer.dto.activity.WeeklyActivityDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface CoursesService {

    CourseDTO getCourse(long courseId);

    Collection<CourseDTO> getAllCourses();

    Page<CourseDTO> getPageOfCourses(Pageable pageable);

    Collection<CourseDTO> getAllCoursesByLecturerId(long lecturerId);

    Page<CourseDTO> getPageOfCoursesByLecturerId(long lecturerId, Pageable pageable);

    void addStudentToCourse(long courseId, long studentId);

    void addActivityToACourse(long courseId, WeeklyActivityDTO weeklyActivity);

    void createCourse(long lecturerId, CourseDTO courseDTO);

    void updateCourse(long id, CourseDTO courseDTO);

    void deleteCourse(long id);

}
