package com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class AttemptRequestBody {

    private List<ResultRequestBody> results;
}
