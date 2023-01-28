package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Question, Long> {
}
