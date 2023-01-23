package com.nbu.java.practice.learningprocessorganizer.dao.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "test")
@AllArgsConstructor
@NoArgsConstructor
public class Test extends BaseEntity {

    @Min(value = 2, message = "Grade cannot be less than 2")
    @Max(value = 6, message = "Grade cannot be more than 6")
    private int grade;

    @FutureOrPresent
    private LocalDate openFrom;

    @Future
    private LocalDate openTo;

    @ManyToOne
    private Student student;

    @OneToMany
    private Set<Task> tasks;

    @OneToOne(mappedBy = "test")
    private WeeklyActivity weeklyActivity;
}
