package com.example.projectportal.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "project_id")
    private Long projectId;

    private String status;

    @Column(name = "applied_at")
    private LocalDate appliedAt;

    public Application() {}

    public Application(Long studentId, Long projectId, String status, LocalDate appliedAt) {
        this.studentId = studentId;
        this.projectId = projectId;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDate appliedAt) { this.appliedAt = appliedAt; }
}
