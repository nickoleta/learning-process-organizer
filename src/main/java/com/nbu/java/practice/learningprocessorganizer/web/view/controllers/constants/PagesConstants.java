package com.nbu.java.practice.learningprocessorganizer.web.view.controllers.constants;

public final class PagesConstants {

    public static final String LOGIN = "login";
    public static final String INDEX_ADMIN = "index-admin";
    public static final String INDEX_LECTURER = "index-lecturer";
    public static final String INDEX_STUDENT = "index-student";
    public static final String UNAUTHORIZED = "unauthorized";
    public static final String NOT_FOUND_RESOURCE = "not-found";

    public static final String LECTURERS = "/lecturers/lecturers";
    public static final String LECTURERS_REDIRECT = "redirect:/lecturers";
    public static final String LECTURERS_REDIRECT_PAGING = "redirect:/lecturers/page/1/size/10";
    public static final String LECTURER_CREATE = "/lecturers/create-lecturer";
    public static final String LECTURER_EDIT = "/lecturers/edit-lecturer";

    public static final String STUDENTS = "/students/students";
    public static final String STUDENTS_LECTURER = "/students/students-lecturer";
    public static final String STUDENTS_REDIRECT = "redirect:/students";
    public static final String STUDENTS_REDIRECT_PAGING = "redirect:/students/page/1/size/10";
    public static final String STUDENTS_CREATE = "/students/create-student";
    public static final String STUDENTS_EDIT = "/students/edit-student";

    public static final String COURSES = "/courses/courses";
    public static final String COURSES_STUDENT = "/courses/courses-student";
    public static final String COURSES_REDIRECT = "redirect:/courses";
    public static final String COURSES_REDIRECT_PAGING = "redirect:/courses/page/1/size/10";
    public static final String MY_COURSES_REDIRECT_PAGING = "redirect:/courses/registered/page/1/size/10";
    public static final String COURSES_CREATE = "/courses/create-course";
    public static final String COURSES_EDIT = "/courses/edit-course";
    public static final String MY_COURSES_LECTURER = "/courses/my-courses-lecturer";
    public static final String MY_COURSES_STUDENT = "/courses/my-courses-student";
    public static final String COURSE_DATA_LECTURER = "/courses/course-data";

    public static final String ACTIVITIES_CREATE = "/activities/create-activity";


    private PagesConstants() {
    }
}
