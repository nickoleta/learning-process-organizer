package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "activity")
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyActivity extends BaseEntity {

    @Column(name = "start_date")
    private LocalDate startDate;

    @FutureOrPresent
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "weeklyActivity", cascade = CascadeType.ALL)
    private List<StudyMaterial> studyMaterials;

    @OneToMany(mappedBy = "weeklyActivity")
    private List<Homework> homeworks;

    @OneToOne(mappedBy = "weeklyActivity", cascade = CascadeType.ALL)
    private Exam exam;

}
