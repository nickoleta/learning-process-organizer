package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface CoursesRepository extends PagingAndSortingRepository<Course, Long> {

    Collection<Course> findAllByLecturerId(long lecturerId);

}
