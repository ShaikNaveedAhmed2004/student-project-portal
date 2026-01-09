package com.example.projectportal.service;

import com.example.projectportal.dto.ApplicationDto;
import com.example.projectportal.model.Application;
import com.example.projectportal.model.Student;

import java.util.List;

public interface ApplicationService {
    String applyForProject(ApplicationDto dto);
    List<Application> getApplicationsByStudent(Long studentId);
    List<Application> getApplicationsByProject(Long projectId);
    void updateStatus(Long applicationId, String status);
    List<Application> getApplicationsByFaculty(Long facultyId);
    List<Student> getStudentsByProjectId(Long projectId);
    String deleteById(Long applicationId);
    String withdraw(Long applicationId,Long studentId);
}
