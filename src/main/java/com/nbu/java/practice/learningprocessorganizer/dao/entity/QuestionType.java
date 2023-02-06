package com.nbu.java.practice.learningprocessorganizer.dao.entity;

public enum QuestionType {

    OPEN_ANSWER("Open answer"), MULTIPLE_CHOICE("Multiple choice"), TRUE_FALSE("True or False");

    private final String viewName;

    QuestionType(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
