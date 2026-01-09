package com.example.projectportal.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private LocalDate deadline;

    @Column(name = "faculty_id")
    private Long facultyId;

    private int projectCapacity;

    public Project() {}

    public Project(String title, String description, LocalDate deadline, Long facultyId, int projectCapacity) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.facultyId = facultyId;
        this.projectCapacity = projectCapacity;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public Long getFacultyId() { return facultyId; }
    public void setFacultyId(Long facultyId) { this.facultyId = facultyId; }

    public int getProjectCapacity() { return projectCapacity; }
    public void setProjectCapacity(int projectCapacity) { this.projectCapacity = projectCapacity; }
}
