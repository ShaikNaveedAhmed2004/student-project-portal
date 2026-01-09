package com.example.projectportal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    private String branch;

    private String year;

    public Student() {}

    public Student(String year, String branch, long userId) {
        this.year = year;
        this.branch = branch;
        this.userId = userId;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
}
