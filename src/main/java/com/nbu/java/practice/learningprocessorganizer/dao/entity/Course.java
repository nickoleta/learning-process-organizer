package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity {

    @NotBlank
    @Size(max = 30)
    @Column(name = "name")
    private String name;

    @ManyToOne
    private Lecturer lecturer;

    @ManyToMany
    private Set<Student> students;

    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }

}
