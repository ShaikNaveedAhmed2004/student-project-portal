package com.example.projectportal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    private String department;

    public Faculty() {}

    public Faculty(String department, long userId) {
        this.department = department;
        this.userId = userId;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
