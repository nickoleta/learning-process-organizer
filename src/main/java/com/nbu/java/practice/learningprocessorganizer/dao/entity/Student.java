package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {

    @NotBlank
    @Size(min = 6, max = 6)
    private String fn;

    @NotBlank
    @Size(max = 30)
    private String name;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    public void addCourse(Course course) {
        if (course == null) {
            return;
        }
        courses.add(course);
    }

}
