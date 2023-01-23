package com.nbu.java.practice.learningprocessorganizer.web.view.controllers;

import com.nbu.java.practice.learningprocessorganizer.annotations.AnyUser;
import com.nbu.java.practice.learningprocessorganizer.annotations.Lecturer;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.UserIdentity;
import com.nbu.java.practice.learningprocessorganizer.dto.UserRole;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.CourseDTO;
import com.nbu.java.practice.learningprocessorganizer.service.CoursesService;
import com.nbu.java.practice.learningprocessorganizer.web.view.controllers.constants.PagesConstants;
import com.nbu.java.practice.learningprocessorganizer.web.view.controllers.constants.SortingConstants;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.courses.CourseViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.courses.CreateCourseViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Controller
@RequestMapping("/courses")
public class CoursesController {

    private final CoursesService coursesService;
    private final ModelMapper modelMapper;

    @Lecturer
    @GetMapping("/create-course")
    public String showCreateCourseForm(Model model) {
        model.addAllAttributes(Map.of(
                "course", new CreateCourseViewModel()
        ));
        return PagesConstants.COURSES_CREATE;
    }

    @Lecturer
    @PostMapping("/create")
    public String createCourse(@Valid @ModelAttribute("course") CreateCourseViewModel createCourseViewModel,
                               BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return PagesConstants.COURSES_CREATE;
        }
        final var lecturerId = ((UserIdentity) authentication.getPrincipal()).getId();
        coursesService.createCourse(lecturerId, modelMapper.map(createCourseViewModel, CourseDTO.class));
        return PagesConstants.COURSES_REDIRECT;
    }

    @AnyUser
    @GetMapping("/page/{page}/size/{size}/sort/{sortCriteria}/direction/{sortDirection}")
    public String getCoursesOrderedByName(Model model, Authentication authentication,
                                          @PathVariable("page") final Integer page, @PathVariable("size") final Integer size,
                                          @PathVariable("sortCriteria") final String sortCriteria,
                                          @PathVariable("sortDirection") final String sortDirection) {
        PageRequest pageRequest;
        if ("asc".equalsIgnoreCase(sortDirection)) {
            pageRequest = PageRequest.of(page - 1, size, Sort.by(sortCriteria).ascending());
        } else {
            pageRequest = PageRequest.of(page - 1, size, Sort.by(sortCriteria).descending());
        }
        final var courses = coursesService.getPageOfCourses(pageRequest);
        final var direction = sortDirection.equals(SortingConstants.DESC_SORT_DIRECTION) ? SortingConstants.ASC_SORT_DIRECTION : SortingConstants.DESC_SORT_DIRECTION;
        return getCourses(courses, model, page, sortCriteria, direction, authentication);
    }

    @AnyUser
    @GetMapping("/page/{page}/size/{size}")
    public String getCourses(Model model, Authentication authentication,
                             @PathVariable("page") final int page, @PathVariable("size") final int size) {
        final var courses = coursesService.getPageOfCourses(PageRequest.of(page - 1, size));
        return getCourses(courses, model, page, SortingConstants.ID_SORT_CRITERIA, SortingConstants.ASC_SORT_DIRECTION, authentication);
    }

    @AnyUser
    @GetMapping({"/", ""})
    public String getCourses() {
        return PagesConstants.COURSES_REDIRECT_PAGING;
    }

    private String getCourses(Page<CourseDTO> courses, Model model, int page, String sortCriteria, String sortDirection, Authentication authentication) {
        final Page<CourseViewModel> pageOfCourses = modelMapper.map(courses, new TypeToken<Page<CourseViewModel>>() {
        }.getType());

        model.addAllAttributes(Map.of(
                "pageOfCourses", pageOfCourses,
                "currentPage", page,
                "sortCriteria", sortCriteria,
                "sortDirection", sortDirection));
        int totalPages = pageOfCourses.getTotalPages();
        if (totalPages > 0) {
            model.addAttribute("pageNumbers", getPageNumbers(totalPages));
        }

        final var role = getRoleName(authentication);
        if (role == null) {
            return PagesConstants.UNAUTHORIZED;
        }
        if (UserRole.LECTURER_ROLE.getRoleName().equalsIgnoreCase(role)) {
            return PagesConstants.COURSES;
        }
        if (UserRole.STUDENT_ROLE.getRoleName().equalsIgnoreCase(role)) {
            model.addAttribute("studentId", ((UserIdentity) authentication.getPrincipal()).getStudent().getId());
            return ""; // TODO
        }
        return PagesConstants.UNAUTHORIZED;
    }

    private String getRoleName(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().orElse(null);
    }

    private List<Integer> getPageNumbers(int totalPages) {
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }
}
