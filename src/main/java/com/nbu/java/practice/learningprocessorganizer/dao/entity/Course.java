package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course")
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

    @OneToMany(mappedBy = "course")
    private Set<WeeklyActivity> weeklyActivities;

    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }

    public void addWeeklyActivity(WeeklyActivity weeklyActivity) {
        if (weeklyActivity != null) {
            weeklyActivities.add(weeklyActivity);
        }
    }

}
