package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Lecturer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LecturersRepository extends PagingAndSortingRepository<Lecturer, Long> {
}
