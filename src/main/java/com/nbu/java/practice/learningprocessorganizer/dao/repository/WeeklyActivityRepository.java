package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.WeeklyActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyActivityRepository extends JpaRepository<WeeklyActivity, Long> {
}
