package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.WeeklyActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface WeeklyActivityRepository extends JpaRepository<WeeklyActivity, Long> {

    Collection<WeeklyActivity> findAllByCourseId(long courseId);
}
