package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "answer")
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String value;

    @Column(name = "is_correct")
    private Boolean isCorrect = Boolean.FALSE;
}
