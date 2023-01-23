package com.nbu.java.practice.learningprocessorganizer.web.api;

import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.LecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.UpdateLecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.service.LecturersService;
import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.lecturers.UpdateLecturerRequestBody;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.lecturer.CreateLecturerViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/lecturers")
@AllArgsConstructor
public class LecturersApiController {

    private final LecturersService lecturersService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Collection<LecturerDTO>> getLecturers() {
        return ResponseEntity.ok(lecturersService.getAllLecturers());
    }

    @GetMapping("/{lecturerId}")
    public ResponseEntity<LecturerDTO> getLecturer(@PathVariable("lecturerId") final Long lecturerId) {
        return ResponseEntity.ok(lecturersService.getLecturer(lecturerId));
    }

    @PostMapping
    public ResponseEntity<Void> createLecturer(@RequestBody @Valid final CreateLecturerViewModel updateLecturerRequestBody) {
        lecturersService.createLecturer(updateLecturerRequestBody);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{lecturerId}")
    public ResponseEntity<Void> updateLecturer(@PathVariable("lecturerId") final Long lecturerId,
                                               @RequestBody @Valid final UpdateLecturerRequestBody updateLecturerRequestBody) {
        lecturersService.updateLecturer(lecturerId, modelMapper.map(updateLecturerRequestBody, UpdateLecturerDTO.class));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{lecturerId}")
    public ResponseEntity<Void> deleteLecturer(@PathVariable("lecturerId") final Long lecturerId) {
        lecturersService.deleteLecturer(lecturerId);
        return ResponseEntity.ok().build();
    }


}
