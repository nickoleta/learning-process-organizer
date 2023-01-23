package com.nbu.java.practice.learningprocessorganizer.web.view.model.lecturer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class LecturerViewModel {

    @NotBlank
    @Size(max = 25)
    private String name;
}
