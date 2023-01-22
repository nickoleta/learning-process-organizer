package com.nbu.java.practice.learningprocessorganizer.controller;

import com.nbu.java.practice.learningprocessorganizer.controller.dto.request.students.CreateStudentRequestBody;
import com.nbu.java.practice.learningprocessorganizer.controller.dto.request.students.UpdateStudentRequestBody;
import com.nbu.java.practice.learningprocessorganizer.dto.students.CreateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.students.StudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.students.UpdateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.service.StudentsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentsApiController {

    private final StudentsService studentsService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> getStudents() {
        return ResponseEntity.ok(studentsService.getAllStudents());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("studentId") final Long studentId) {
        return ResponseEntity.ok(studentsService.getStudent(studentId));
    }

    @PostMapping
    public ResponseEntity<Void> createStudent(@RequestBody final CreateStudentRequestBody updateStudentDTO) {
        studentsService.createStudent(modelMapper.map(updateStudentDTO, CreateStudentDTO.class));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable("studentId") final Long studentId,
                                              @RequestBody final UpdateStudentRequestBody updateStudentRequestBody) {
        studentsService.updateStudent(studentId, modelMapper.map(updateStudentRequestBody, UpdateStudentDTO.class));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") final Long studentId) {
        studentsService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
}
