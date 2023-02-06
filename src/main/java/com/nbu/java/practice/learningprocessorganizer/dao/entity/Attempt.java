package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "attempt")
@AllArgsConstructor
@NoArgsConstructor
public class Attempt extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attempt")
    private List<Result> results = new java.util.ArrayList<>();

    @Min(2)
    @Max(6)
    private double grade;

    public Attempt(Student student, Exam exam, List<Result> results) {
        this.student = student;
        this.exam = exam;
        this.results = results;
        this.grade = 2;
    }

}