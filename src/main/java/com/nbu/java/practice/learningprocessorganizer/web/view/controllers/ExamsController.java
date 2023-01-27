package com.nbu.java.practice.learningprocessorganizer.web.view.controllers;

import com.nbu.java.practice.learningprocessorganizer.annotations.Lecturer;
import com.nbu.java.practice.learningprocessorganizer.annotations.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/exams")
public class ExamsController {

    @Student
    @GetMapping("/take-exam")
    public String showTakeExamView() {
        return "/exams/take-exam";
    }

    @Lecturer
    @GetMapping("/create-task")
    public String showCreateTaskView(Model model) {
        model.addAttribute("");
        return "/exams/create-task";
    }

}
