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

    Page<CourseDTO> getPageOfCoursesByLecturerId(long lecturerId, Pageable pageable);

    Page<CourseDTO> getPageOfCoursesByStudentId(long studentId, Pageable pageable);

    void addStudentToCourse(long courseId, long studentId);

    void removeStudentToCourse(long courseId, long studentId);

    void addActivityToACourse(long courseId, WeeklyActivityDTO weeklyActivity);

    void createCourse(long lecturerId, CourseDTO courseDTO);

}
