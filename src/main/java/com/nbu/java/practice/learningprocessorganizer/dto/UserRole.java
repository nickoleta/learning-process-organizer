package com.nbu.java.practice.learningprocessorganizer.dto;

public enum UserRole {

    ADMIN_ROLE("ADMIN"), LECTURER_ROLE("LECTURER"), STUDENT_ROLE("STUDENT");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
