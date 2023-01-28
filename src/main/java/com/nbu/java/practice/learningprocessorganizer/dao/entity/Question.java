package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseEntity {

    @NotBlank
    private String question;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @NotNull
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

}
