package com.nbu.java.practice.learningprocessorganizer.web.view.model.activities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class ActivityViewModel {

    private Long id;


    private LocalDate startDate;
    private LocalDate endDate;
    private Set<StudyMaterialViewModel> studyMaterials;
    private ExamViewModel exam;

}
