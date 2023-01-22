package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.StudyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyMaterialsRepository extends JpaRepository<StudyMaterial, Long> {
}
