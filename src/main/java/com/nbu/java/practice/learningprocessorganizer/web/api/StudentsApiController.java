package com.nbu.java.practice.learningprocessorganizer.web.api;

import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.students.CreateStudentRequestBody;
import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.students.UpdateStudentRequestBody;
import com.nbu.java.practice.learningprocessorganizer.dto.students.CreateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.students.StudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.students.UpdateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.service.StudentsService;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.students.CreateStudentViewModel;
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
    public ResponseEntity<Void> createStudent(@RequestBody @Valid final CreateStudentRequestBody updateStudentDTO) {
        studentsService.createStudent(modelMapper.map(updateStudentDTO, CreateStudentViewModel.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable("studentId") final Long studentId,
                                              @RequestBody @Valid final UpdateStudentRequestBody updateStudentRequestBody) {
        studentsService.updateStudent(studentId, modelMapper.map(updateStudentRequestBody, UpdateStudentDTO.class));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") final Long studentId) {
        studentsService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
}
