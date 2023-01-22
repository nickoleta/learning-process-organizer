package com.nbu.java.practice.learningprocessorganizer.dto;

public enum UserRole {

    ADMIN_ROLE("ADMIN"), DOCTOR_ROLE("DOCTOR"), PATIENT_ROLE("PATIENT");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
