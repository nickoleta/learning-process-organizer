package com.nbu.java.practice.learningprocessorganizer.web.view.controllers;

import com.nbu.java.practice.learningprocessorganizer.annotations.AnyUser;
import com.nbu.java.practice.learningprocessorganizer.annotations.Lecturer;
import com.nbu.java.practice.learningprocessorganizer.annotations.LecturerOrStudent;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.UserIdentity;
import com.nbu.java.practice.learningprocessorganizer.dto.UserRole;
import com.nbu.java.practice.learningprocessorganizer.dto.activity.WeeklyActivityDTO;
import com.nbu.java.practice.learningprocessorganizer.dto.courses.CourseDTO;
import com.nbu.java.practice.learningprocessorganizer.service.ActivitiesService;
import com.nbu.java.practice.learningprocessorganizer.service.CoursesService;
import com.nbu.java.practice.learningprocessorganizer.service.StudyMaterialsService;
import com.nbu.java.practice.learningprocessorganizer.web.view.controllers.constants.PagesConstants;
import com.nbu.java.practice.learningprocessorganizer.web.view.controllers.constants.SortingConstants;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.WeeklyActivityViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.courses.CourseViewModel;
import com.nbu.java.practice.learningprocessorganizer.web.view.model.courses.CreateCourseViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Controller
@RequestMapping("/courses")
public class CoursesController {

    private final CoursesService coursesService;
    private final ActivitiesService activitiesService;
    private final StudyMaterialsService studyMaterialsService;
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

    @LecturerOrStudent
    @GetMapping("/registered/page/{page}/size/{size}/sort/{sortCriteria}/direction/{sortDirection}")
    public String getMyCourses(Model model, Authentication authentication,
                               @PathVariable("page") final Integer page, @PathVariable("size") final Integer size,
                               @PathVariable("sortCriteria") final String sortCriteria,
                               @PathVariable("sortDirection") final String sortDirection) {
        final var pageRequest = createPageRequest(sortDirection, page, size, sortCriteria);
        final var courses = getMyCoursesBasedOnUserRole(authentication, pageRequest);
        final var direction = sortDirection.equals(SortingConstants.DESC_SORT_DIRECTION) ? SortingConstants.ASC_SORT_DIRECTION : SortingConstants.DESC_SORT_DIRECTION;
        return getCourses(courses, model, page, sortCriteria, direction, authentication, true);
    }

    @LecturerOrStudent
    @GetMapping("/registered/page/{page}/size/{size}")
    public String getMyCourses(Model model, Authentication authentication,
                               @PathVariable("page") final Integer page, @PathVariable("size") final Integer size) {
        final var courses = getMyCoursesBasedOnUserRole(authentication, PageRequest.of(page - 1, size));
        return getCourses(courses, model, page, SortingConstants.ID_SORT_CRITERIA, SortingConstants.ASC_SORT_DIRECTION, authentication, true);
    }

    @AnyUser
    @GetMapping("/page/{page}/size/{size}/sort/{sortCriteria}/direction/{sortDirection}")
    public String getCoursesOrderedByName(Model model, Authentication authentication,
                                          @PathVariable("page") final Integer page, @PathVariable("size") final Integer size,
                                          @PathVariable("sortCriteria") final String sortCriteria,
                                          @PathVariable("sortDirection") final String sortDirection) {
        final var pageRequest = createPageRequest(sortDirection, page, size, sortCriteria);
        final var courses = coursesService.getPageOfCourses(pageRequest);
        final var direction = sortDirection.equals(SortingConstants.DESC_SORT_DIRECTION) ? SortingConstants.ASC_SORT_DIRECTION : SortingConstants.DESC_SORT_DIRECTION;
        return getCourses(courses, model, page, sortCriteria, direction, authentication, false);
    }

    @AnyUser
    @GetMapping("/page/{page}/size/{size}")
    public String getCourses(Model model, Authentication authentication,
                             @PathVariable("page") final int page, @PathVariable("size") final int size) {
        final var courses = coursesService.getPageOfCourses(PageRequest.of(page - 1, size));
        return getCourses(courses, model, page, SortingConstants.ID_SORT_CRITERIA, SortingConstants.ASC_SORT_DIRECTION, authentication, false);
    }

    @AnyUser
    @GetMapping({"/", ""})
    public String getCourses() {
        return PagesConstants.COURSES_REDIRECT_PAGING;
    }

    @AnyUser
    @GetMapping({"/registered"})
    public String getMyCourses() {
        return PagesConstants.MY_COURSES_REDIRECT_PAGING;
    }

    @Lecturer
    @GetMapping("/{courseId}/data")
    public String showCourseData(@PathVariable("courseId") final Long courseId, Model model) {
        model.addAttribute("course", modelMapper.map(coursesService.getCourse(courseId), CourseViewModel.class));
        return PagesConstants.COURSE_DATA_LECTURER;
    }

    @Lecturer
    @GetMapping("/{courseId}/activities/create-activity")
    public String showCreateActivity(Model model, @PathVariable("courseId") final Long courseId) {
        model.addAllAttributes(Map.of(
                "courseId", courseId,
                "activity", new WeeklyActivityViewModel()));
        return PagesConstants.ACTIVITIES_CREATE;
    }

    @Lecturer
    @PostMapping("/{courseId}/activities/create")
    public String createActivity(@PathVariable("courseId") final Long courseId,
                                 @RequestParam("file") MultipartFile file,
                                 @ModelAttribute("activity") @Valid WeeklyActivityViewModel weeklyActivityViewModel,
                                 BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            return PagesConstants.ACTIVITIES_CREATE;
        }

        final var activity = modelMapper.map(weeklyActivityViewModel, WeeklyActivityDTO.class);
        coursesService.addActivityToACourse(courseId, activity);
        model.addAttribute("course", modelMapper.map(coursesService.getCourse(courseId), CourseViewModel.class));

        if (!file.isEmpty()) {
            final var latestActivityId = activitiesService.getActivitiesByCourseId(courseId).stream()
                    .map(WeeklyActivityDTO::getId)
                    .mapToLong(Long::longValue).max();
            studyMaterialsService.uploadFile(file, latestActivityId.getAsLong());
        }

        return PagesConstants.COURSE_DATA_LECTURER;
    }

    @GetMapping(path = "/download/{fileId}")
    public ResponseEntity<byte[]> download(@PathVariable("fileId") final Long fileId) {
        final var studyMaterial = studyMaterialsService.getFile(fileId);

        return ResponseEntity.ok().headers(new HttpHeaders())
                .contentLength(studyMaterial.getData().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(studyMaterial.getData());
    }

    @Lecturer
    @GetMapping("/{courseId}/activities/{activityId}")
    public String deleteActivity(@PathVariable("courseId") final Long courseId, @PathVariable("activityId") final Long activityId, Model model) {
        activitiesService.deleteActivity(activityId);
        model.addAttribute("course", modelMapper.map(coursesService.getCourse(courseId), CourseViewModel.class));
        return PagesConstants.COURSE_DATA_LECTURER;
    }

    @PostMapping("/activities/{activityId}/upload")
    public String uploadFile(@PathVariable("activityId") final Long activityId,
                             @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        try {
            studyMaterialsService.uploadFile(file, activityId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded the file");
        return "redirect:/";
    }

    private PageRequest createPageRequest(String sortDirection, int page, int size, String sortCriteria) {
        if ("asc".equalsIgnoreCase(sortDirection)) {
            return PageRequest.of(page - 1, size, Sort.by(sortCriteria).ascending());
        }
        return PageRequest.of(page - 1, size, Sort.by(sortCriteria).descending());
    }

    private String getCourses(Page<CourseDTO> courses, Model model, int page, String sortCriteria, String sortDirection,
                              Authentication authentication, boolean searchingForMyCourses) {
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
            return searchingForMyCourses ? PagesConstants.MY_COURSES_LECTURER : PagesConstants.COURSES;
        }
        if (UserRole.STUDENT_ROLE.getRoleName().equalsIgnoreCase(role)) {
            return searchingForMyCourses ? PagesConstants.MY_COURSES_STUDENT : PagesConstants.COURSES_STUDENT;
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

    private Page<CourseDTO> getMyCoursesBasedOnUserRole(Authentication authentication, PageRequest pageRequest) {
        if (UserRole.LECTURER_ROLE.getRoleName().equalsIgnoreCase(getRoleName(authentication))) {
            final var lecturerId = ((UserIdentity) authentication.getPrincipal()).getLecturer().getId();
            return coursesService.getPageOfCoursesByLecturerId(lecturerId, pageRequest);
        }
        final var studentId = ((UserIdentity) authentication.getPrincipal()).getStudent().getId();
        return coursesService.getPageOfCoursesByStudentId(studentId, pageRequest);
    }
}
