package com.example.projectportal.dto;

public class ApplicationDto {
    private Long studentId;
    private Long projectId;
    private String role;

    public ApplicationDto() {}

    public ApplicationDto(Long studentId, Long projectId, String role) {
        this.studentId = studentId;
        this.projectId = projectId;
        this.role = role;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
