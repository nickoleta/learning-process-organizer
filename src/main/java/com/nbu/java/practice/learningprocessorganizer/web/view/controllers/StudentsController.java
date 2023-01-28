package com.nbu.java.practice.learningprocessorganizer.web.view.controllers;

import com.nbu.java.practice.learningprocessorganizer.annotations.Admin;
import com.nbu.java.practice.learningprocessorganizer.annotations.AdminOrLecturer;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.UserIdentity;
import com.nbu.java.practice.learningprocessorganizer.dto.UserRole;
import com.nbu.java.practice.learningprocessorganizer.dto.students.StudentDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.students.UpdateStudentDTO;
import com.nbu.java.practice.learningprocessorganizer.exceptions.ResourceNotFoundException;
import com.nbu.java.practice.learningprocessorganizer.service.StudentsService;
import com.nbu.java.practice.learningprocessorganizer.web.view.controllers.constants.PagesConstants;
import com.nbu.java.practice.learningprocessorganizer.web.view.controllers.constants.SortingConstants;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.students.CreateStudentViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.students.StudentViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.students.UpdateStudentViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/students")
@AllArgsConstructor
public class StudentsController {

    private final StudentsService studentsService;
    private final ModelMapper modelMapper;

    @Admin
    @GetMapping("/create-student")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new CreateStudentViewModel());
        return PagesConstants.STUDENTS_CREATE;
    }

    @Admin
    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") CreateStudentViewModel createStudentViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PagesConstants.STUDENTS_CREATE;
        }

        studentsService.createStudent(createStudentViewModel);
        return PagesConstants.STUDENTS_REDIRECT;
    }

    @Admin
    @GetMapping("/edit-student/{id}")
    public String showEditStudentForm(Model model, @PathVariable final long id) {
        final var student = studentsService.getStudent(id);
        if (student == null) {
            throw new ResourceNotFoundException(ResourceNotFoundException.STUDENT_DOES_NOT_EXIST, id);
        }
        model.addAttribute("student", student);
        return PagesConstants.STUDENTS_EDIT;
    }

    @Admin
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable long id, @Valid @ModelAttribute("student") final UpdateStudentViewModel updateStudentViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PagesConstants.STUDENTS_EDIT;
        }
        studentsService.updateStudent(id, modelMapper.map(updateStudentViewModel, UpdateStudentDTO.class));
        return PagesConstants.STUDENTS_REDIRECT;
    }

    @Admin
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentsService.deleteStudent(id);
        return PagesConstants.STUDENTS_REDIRECT;
    }

    @AdminOrLecturer
    @GetMapping("/page/{page}/size/{size}/sort/{sortCriteria}/direction/{sortDirection}")
    public String getStudentsOrderByName(Model model, Authentication authentication,
                                         @PathVariable("page") final Integer page,
                                         @PathVariable("size") final Integer size,
                                         @PathVariable("sortCriteria") final String sortCriteria,
                                         @PathVariable("sortDirection") final String sortDirection,
                                         @RequestParam(value = "searchCriteria", required = false) final String searchCriteria,
                                         @RequestParam(value = "courseId", required = false) final String courseId) {
        PageRequest pageRequest;
        if (SortingConstants.ASC_SORT_DIRECTION.equalsIgnoreCase(sortDirection)) {
            pageRequest = PageRequest.of(page - 1, size, Sort.by(sortCriteria).ascending());
        } else {
            pageRequest = PageRequest.of(page - 1, size, Sort.by(sortCriteria).descending());
        }
        Page<StudentDTO> students;
        if (searchCriteria == null || searchCriteria.isEmpty()) {
            students = studentsService.getPageOfStudents(pageRequest);
        } else {
            model.addAttribute("searchCriteria", searchCriteria);
            students = studentsService.getPageOfStudents(pageRequest, searchCriteria);
        }
        final var direction = sortDirection.equals(SortingConstants.DESC_SORT_DIRECTION) ? SortingConstants.ASC_SORT_DIRECTION : SortingConstants.DESC_SORT_DIRECTION;
        return getStudents(students, model, page, sortCriteria, direction, searchCriteria, authentication);
    }

    @AdminOrLecturer
    @GetMapping("/page/{page}/size/{size}")
    public String getStudents(Model model, Authentication authentication,
                              @PathVariable("page") final int page, @PathVariable("size") final int size) {
        final var students = studentsService.getPageOfStudents(PageRequest.of(page - 1, size));
        return getStudents(students, model, page, SortingConstants.ID_SORT_CRITERIA, SortingConstants.ASC_SORT_DIRECTION, "", authentication);
    }

    @AdminOrLecturer
    @GetMapping(path = {"/", ""})
    public String getStudents() {
        return PagesConstants.STUDENTS_REDIRECT_PAGING;
    }

    private String getStudents(Page<StudentDTO> students, Model model, int page, String sortCriteria, String sortDirection, String searchCriteria, Authentication authentication) {
        final Page<StudentViewModel> pageOfStudents = modelMapper.map(students, new TypeToken<Page<StudentViewModel>>() {
        }.getType());

        model.addAllAttributes(Map.of(
                "pageOfStudents", pageOfStudents,
                "currentPage", page,
                "sortCriteria", sortCriteria,
                "sortDirection", sortDirection,
                " searchCriteria", searchCriteria == null ? "" : searchCriteria));
        int totalPages = pageOfStudents.getTotalPages();
        if (totalPages > 0) {
            model.addAttribute("pageNumbers", getPageNumbers(totalPages));
        }
        final var authority = ((UserIdentity) authentication.getPrincipal()).getAuthority().getAuthority();
        if (UserRole.LECTURER_ROLE.getRoleName().equalsIgnoreCase(authority)) {
            return PagesConstants.STUDENTS_LECTURER;
        }
        if (UserRole.ADMIN_ROLE.getRoleName().equalsIgnoreCase(authority)) {
            return PagesConstants.STUDENTS;
        }
        return PagesConstants.UNAUTHORIZED;
    }

    private List<Integer> getPageNumbers(int totalPages) {
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }
}
