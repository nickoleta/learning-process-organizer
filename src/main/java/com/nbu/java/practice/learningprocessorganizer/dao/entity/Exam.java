package com.nbu.java.practice.learningprocessorganizer.dao.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "exam")
@AllArgsConstructor
@NoArgsConstructor
public class Exam extends BaseEntity {

    @FutureOrPresent
    private LocalDate openFrom;

    @Future
    private LocalDate openTo;

    @OneToMany(mappedBy = "exam")
    private List<Question> questions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exam")
    private List<Attempt> attempts = new java.util.ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", unique = true)
    private WeeklyActivity weeklyActivity;

    private boolean isPublished;

}
