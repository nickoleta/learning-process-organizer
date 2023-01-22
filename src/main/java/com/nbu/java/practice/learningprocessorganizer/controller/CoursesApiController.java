package com.nbu.java.practice.learningprocessorganizer.controller;

import com.nbu.java.practice.learningprocessorganizer.controller.dto.request.courses.CreateCourseRequestBody;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.CourseDTO;
import com.nbu.java.practice.learningprocessorganizer.service.CoursesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesApiController {

    private final CoursesService coursesService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Collection<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(coursesService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable("courseId") final Long courseId) {
        return ResponseEntity.ok(coursesService.getCourse(courseId));
    }

    @PostMapping
    public ResponseEntity<Void> createCourse(@RequestBody @Valid CreateCourseRequestBody createCourseRequestBody) {
        coursesService.createCourse(createCourseRequestBody.getLecturerId(), modelMapper.map(createCourseRequestBody, CourseDTO.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{courseId}/students")
    public ResponseEntity<Void> addStudentToACourse(@PathVariable("courseId") final Long courseId,
                                                    @RequestParam("studentId") final Long studentId) {
        coursesService.addStudentToCourse(courseId, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
