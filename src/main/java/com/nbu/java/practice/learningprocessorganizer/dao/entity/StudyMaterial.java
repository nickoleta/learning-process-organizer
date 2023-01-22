package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "material")
@NoArgsConstructor
@AllArgsConstructor
public class StudyMaterial extends BaseEntity {

    @NotBlank
    private String name;

    @NotBlank
    private String contentType;

    @Lob
    private byte[] data;

    @ManyToOne
    private WeeklyActivity weeklyActivity;
}
