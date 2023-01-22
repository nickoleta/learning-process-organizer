package com.nbu.java.practice.learningprocessorganizer.controller.dto.request.files;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileRequestBody {

    @NotBlank(message = "File name is required!")
    @Size(max = 15)
    private String name;

    @NotBlank(message = "Content type is required!")
    private String contentType;

    private byte[] data;
}
