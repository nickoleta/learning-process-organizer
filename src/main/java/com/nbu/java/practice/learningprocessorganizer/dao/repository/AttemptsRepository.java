package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttemptsRepository extends JpaRepository<Attempt, Long> {

    Optional<Attempt> findByStudentIdAndExamId(long studentId, long examId);
}
