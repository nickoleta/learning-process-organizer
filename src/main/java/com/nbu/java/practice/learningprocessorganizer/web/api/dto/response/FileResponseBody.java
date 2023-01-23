package com.nbu.java.practice.learningprocessorganizer.web.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class FileResponseBody {

    private String name;
    private String downloadUrl;
    private String contentType;
    private long size;
}
