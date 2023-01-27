package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface CoursesRepository extends PagingAndSortingRepository<Course, Long> {

    Collection<Course> findAllByLecturer_id(long lecturerId);

    Page<Course> findAllByLecturer_id(long lecturerId, Pageable pageable);

    Page<Course> findByStudents_Id(long studentId, Pageable pageable);

}
