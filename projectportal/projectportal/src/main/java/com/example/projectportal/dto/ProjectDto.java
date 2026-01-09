package com.example.projectportal.dto;
import java.time.LocalDate;

public class ProjectDto {
    private String title;
    private String description;
    private String deadline;
    private Long facultyId;
    private String role;
    private int projectCapacity;

    public ProjectDto() {}

    public ProjectDto(String title, String description, String deadline, Long facultyId, String role, int projectCapacity) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.facultyId = facultyId;
        this.role = role;
        this.projectCapacity = projectCapacity;
    }



    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Long getFacultyId() {
        return facultyId;
    }
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public int getProjectCapacity() {
        return projectCapacity;
    }
    public void setProjectCapacity(int projectCapacity) {
        this.projectCapacity = projectCapacity;
    }
}
