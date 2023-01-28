package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultsRepository extends JpaRepository<Result, Long> {
}
