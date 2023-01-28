package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentsRepository extends PagingAndSortingRepository<Student, Long> {

    Page<Student> findAllByNameContainsOrFnContains(Pageable pageable, String name, String fn);

}
