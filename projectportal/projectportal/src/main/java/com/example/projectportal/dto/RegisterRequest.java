package com.example.projectportal.dto;

public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String role;

    private long userId;


    // only for students
    private String branch;
    private String year;

    // only for faculty
    private String department;

    public RegisterRequest() {}

    public RegisterRequest(String name, String email, String password, String role, String branch, String year) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.branch = branch;
        this.year = year;
    }

    public RegisterRequest(String name, String email, String password, String role,String department){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
