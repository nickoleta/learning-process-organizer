package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
public class Task extends BaseEntity {

    @NotBlank
    private String question;

    @OneToMany(mappedBy = "task")
    private Set<Answer> answers;

    @Column(name = "given_answer")
    private String givenAnswer;

    @NotNull
    private TaskType taskType;


}
