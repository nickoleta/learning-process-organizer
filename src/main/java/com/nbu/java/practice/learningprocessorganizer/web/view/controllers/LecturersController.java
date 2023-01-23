package com.nbu.java.practice.learningprocessorganizer.web.view.controllers;

import com.nbu.java.practice.learningprocessorganizer.annotations.Admin;
import com.nbu.java.practice.learningprocessorganizer.annotations.AnyUser;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.UserIdentity;
import com.nbu.java.practice.learningprocessorganizer.dto.UserRole;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.CreateLecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.LecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.lecturers.UpdateLecturerDTO;
import com.nbu.java.practice.learningprocessorganizer.service.LecturersService;
import com.nbu.java.practice.learningprocessorganizer.web.view.controllers.constants.PagesConstants;
import com.nbu.java.practice.learningprocessorganizer.web.view.controllers.constants.SortingConstants;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.lecturer.CreateLecturerViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.lecturer.LecturerViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.lecturer.UpdateLecturerViewModel;
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
@RequestMapping("/lecturers")
public class LecturersController {

    private final LecturersService lecturersService;
    private final ModelMapper modelMapper;

    @Admin
    @GetMapping("/create-lecturer")
    public String showCreateLecturerForm(Model model) {
        model.addAllAttributes(Map.of(
                "lecturer", new CreateLecturerViewModel()
        ));
        return PagesConstants.LECTURER_CREATE;
    }

    @Admin
    @PostMapping("/create")
    public String createLecturer(Model model, @Valid @ModelAttribute("lecturer") CreateLecturerViewModel lecturerViewModel,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PagesConstants.LECTURER_CREATE;
        }
        lecturersService.createLecturer(modelMapper.map(lecturerViewModel, CreateLecturerDTO.class));
        return PagesConstants.LECTURERS;
    }

    @Admin
    @GetMapping("/edit-lecturer/{lecturerId}")
    public String showEditLecturerForm(Model model, @PathVariable long lecturerId) {
        model.addAllAttributes(Map.of(
                "lecturer", lecturersService.getLecturer(lecturerId)
        ));
        return PagesConstants.LECTURER_EDIT;
    }

    @Admin
    @PostMapping("/update/{id}")
    public String updateLecturer(@PathVariable long id, @Valid @ModelAttribute("lecturer") UpdateLecturerViewModel updateLecturerViewModel,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PagesConstants.LECTURER_EDIT;
        }
        lecturersService.updateLecturer(id, modelMapper.map(updateLecturerViewModel, UpdateLecturerDTO.class));
        return PagesConstants.LECTURERS_REDIRECT;
    }

    @Admin
    @GetMapping("/delete/{id}")
    public String deleteLecturer(@PathVariable int id) {
        lecturersService.deleteLecturer(id);
        return PagesConstants.LECTURERS_REDIRECT;
    }

    @AnyUser
    @GetMapping("/page/{page}/size/{size}/sort/{sortCriteria}/direction/{sortDirection}")
    public String getLecturersOrderByName(Model model, Authentication authentication,
                                          @PathVariable("page") final Integer page, @PathVariable("size") final Integer size,
                                          @PathVariable("sortCriteria") final String sortCriteria, @PathVariable("sortDirection") final String sortDirection) {
        PageRequest pageRequest;
        if ("asc".equalsIgnoreCase(sortDirection)) {
            pageRequest = PageRequest.of(page - 1, size, Sort.by(sortCriteria).ascending());
        } else {
            pageRequest = PageRequest.of(page - 1, size, Sort.by(sortCriteria).descending());
        }
        final var lecturers = lecturersService.getPageOfLecturers(pageRequest);
        final var direction = sortDirection.equals(SortingConstants.DESC_SORT_DIRECTION) ? SortingConstants.ASC_SORT_DIRECTION : SortingConstants.DESC_SORT_DIRECTION;
        return getLecturers(lecturers, model, page, sortCriteria, direction, authentication);
    }

    @AnyUser
    @GetMapping("/page/{page}/size/{size}")
    public String getLecturers(Model model, Authentication authentication,
                               @PathVariable("page") final int page, @PathVariable("size") final int size) {
        final var lecturers = lecturersService.getPageOfLecturers(PageRequest.of(page - 1, size));
        return getLecturers(lecturers, model, page, SortingConstants.ID_SORT_CRITERIA, SortingConstants.ASC_SORT_DIRECTION, authentication);
    }

    @AnyUser
    @GetMapping({"/", ""})
    public String getLecturers() {
        return PagesConstants.LECTURERS_REDIRECT_PAGING;
    }

    private String getLecturers(Page<LecturerDTO> lecturers, Model model, int page, String sortCriteria, String sortDirection, Authentication authentication) {
        final Page<LecturerViewModel> pageOfLecturers = modelMapper.map(lecturers, new TypeToken<Page<LecturerViewModel>>() {
        }.getType());

        model.addAllAttributes(Map.of(
                "pageOfLecturers", pageOfLecturers,
                "currentPage", page,
                "sortCriteria", sortCriteria,
                "sortDirection", sortDirection));
        int totalPages = pageOfLecturers.getTotalPages();
        if (totalPages > 0) {
            model.addAttribute("pageNumbers", getPageNumbers(totalPages));
        }

        final var role = getRoleName(authentication);
        if (role == null) {
            return PagesConstants.UNAUTHORIZED;
        }
        if (UserRole.ADMIN_ROLE.getRoleName().equalsIgnoreCase(role)) {
            return PagesConstants.LECTURERS;
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
