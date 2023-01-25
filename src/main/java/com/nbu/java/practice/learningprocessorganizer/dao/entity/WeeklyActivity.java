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
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "activity")
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyActivity extends BaseEntity implements Serializable {

    @Column(name = "start_date")
    private LocalDate startDate;

    @FutureOrPresent
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "weeklyActivity", cascade = CascadeType.ALL)
    private Set<StudyMaterial> studyMaterials;

    @OneToMany(mappedBy = "weeklyActivity")
    private Set<Homework> homeworks;

    @OneToOne
    private Test test;

}
