package com.nbu.java.practice.learningprocessorganizer.web.api;

import com.nbu.java.practice.learningprocessorganizer.annotations.Lecturer;
import com.nbu.java.practice.learningprocessorganizer.annotations.Student;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.ExamDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.QuestionDTO;
import com.nbu.java.practice.learningprocessorganizer.service.ActivitiesService;
import com.nbu.java.practice.learningprocessorganizer.service.AttemptsService;
import com.nbu.java.practice.learningprocessorganizer.service.ExamsService;
import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams.AttemptRequestBody;
import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams.ExamRequestBody;
import com.nbu.java.practice.learningprocessorganizer.web.api.dto.request.exams.QuestionRequestBody;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exams")
@AllArgsConstructor
public class ExamsApiController {

    private final ActivitiesService activitiesService;
    private final ExamsService examsService;
    private final AttemptsService attemptsService;
    private final ModelMapper modelMapper;

    @Lecturer
    @PostMapping("/activities/{activityId}")
    public ResponseEntity<Void> createExam(@PathVariable("activityId") final Long activityId, @RequestBody @Valid ExamRequestBody exam) {
        activitiesService.addExamToActivity(activityId, modelMapper.map(exam, ExamDTO.class));
        return ResponseEntity.ok().build();
    }

    @Lecturer
    @PostMapping("/{examId}/questions")
    public ResponseEntity<Void> addQuestionToExam(@PathVariable("examId") final Long examId, @RequestBody @Valid QuestionRequestBody questionRequestBody) {
        examsService.addQuestionToExam(examId, modelMapper.map(questionRequestBody, QuestionDTO.class));
        return ResponseEntity.ok().build();
    }

    @Student
    @PostMapping("/{examId}/{studentId}/attempt")
    public ResponseEntity<Void> makeAttempt(@PathVariable("examId") final Long examId, @PathVariable("studentId") final Long studentId) {
        attemptsService.makeAttempt(studentId, examId);
        return ResponseEntity.ok().build();
    }

    @Student
    @PostMapping("/attempt/{attemptId}")
    public ResponseEntity<Void> addResultsToAttempt(@PathVariable("attemptId") final Long attemptId, @RequestBody @Valid AttemptRequestBody attempt) {
        attemptsService.addResultsToAttempt(attemptId, attempt.getResults());
        return ResponseEntity.ok().build();
    }

}
