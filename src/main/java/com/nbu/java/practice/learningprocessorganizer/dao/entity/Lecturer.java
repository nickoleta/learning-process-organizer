package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.UserIdentity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "lecturer")
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer extends BaseEntity {

    @NotBlank
    @Size(max = 30)
    private String name;

    @OneToMany(mappedBy = "lecturer")
    private Set<Course> courses;

    @OneToOne(mappedBy = "lecturer", cascade = CascadeType.ALL)
    private UserIdentity userIdentity;
}
