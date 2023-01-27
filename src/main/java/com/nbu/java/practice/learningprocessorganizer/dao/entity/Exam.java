package com.nbu.java.practice.learningprocessorganizer.dao.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "exam")
@AllArgsConstructor
@NoArgsConstructor
public class Exam extends BaseEntity {

    @OneToMany(mappedBy = "exam")
    private List<Question> questions;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "exam")
    private List<Attempt> attempts = new java.util.ArrayList<>();

    @OneToOne(mappedBy = "exam")
    private WeeklyActivity weeklyActivity;
}
