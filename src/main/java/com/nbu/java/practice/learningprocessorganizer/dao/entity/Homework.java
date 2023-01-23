package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "homework")
@AllArgsConstructor
@NoArgsConstructor
public class Homework extends BaseEntity {

    @Min(value = 2, message = "Grade cannot be less than 2")
    @Max(value = 6, message = "Grade cannot be more than 6")
    private int grade;

    @FutureOrPresent
    private LocalDate openFrom;

    @Future
    private LocalDate openTo;

    @NotBlank
    private String name;

    @NotBlank
    private String contentType;

    @Lob
    private byte[] data;

    @ManyToOne
    private WeeklyActivity weeklyActivity;

    @ManyToOne
    private Student student;
}
