package com.nbu.java.practice.learningprocessorganizer.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyActivityDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String study;
}
