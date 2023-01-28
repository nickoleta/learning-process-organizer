package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Exam;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamsRepository extends PagingAndSortingRepository<Exam, Long> {
}
