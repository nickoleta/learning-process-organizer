package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CoursesRepository extends PagingAndSortingRepository<Course, Long> {

    Page<Course> findAllByLecturer_id(long lecturerId, Pageable pageable);

    Page<Course> findByStudents_Id(long studentId, Pageable pageable);

}
