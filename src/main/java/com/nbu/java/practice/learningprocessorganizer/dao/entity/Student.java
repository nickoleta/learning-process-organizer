package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.UserIdentity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
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

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Attempt> attempts;

    @OneToMany(mappedBy = "student")
    private List<Homework> homeworks;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private UserIdentity userIdentity;

    public void addCourse(Course course) {
        if (course != null) {
            this.courses.add(course);
        }
    }

}
