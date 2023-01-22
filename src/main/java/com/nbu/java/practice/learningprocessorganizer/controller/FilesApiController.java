package com.nbu.java.practice.learningprocessorganizer.controller;

import com.nbu.java.practice.learningprocessorganizer.controller.dto.response.FileResponseBody;
import com.nbu.java.practice.learningprocessorganizer.service.StudyMaterialsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FilesApiController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final StudyMaterialsService studyMaterialsService;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            studyMaterialsService.uploadFile(file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            logger.error(String.format("Could not upload file with name %s", file.getOriginalFilename()));
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping
    public ResponseEntity<Collection<FileResponseBody>> getAllFiles() {
        final var files = studyMaterialsService.getAllFiles().map(dbFile -> {
            final var fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();
            return new FileResponseBody(dbFile.getName(), fileDownloadUri, dbFile.getContentType(), dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.ok().body(files);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable("fileId") Long id) {
        final var file = studyMaterialsService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

}
