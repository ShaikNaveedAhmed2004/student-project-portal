package com.example.projectportal.controller;

import com.example.projectportal.dto.ApplicationDto;
import com.example.projectportal.model.Application;
import com.example.projectportal.model.Student;
import com.example.projectportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public String applyToProject(@RequestBody ApplicationDto dto) {
        return applicationService.applyForProject(dto);
    }

    @GetMapping("/student/{studentId}")
    public List<Application> getApplicationsByStudent(@PathVariable Long studentId) {
        return applicationService.getApplicationsByStudent(studentId);
    }

    @GetMapping("/project/{projectId}")
    public List<Application> getApplicationsByProject(@PathVariable Long projectId) {
        return applicationService.getApplicationsByProject(projectId);
    }

    @PutMapping("/{applicationId}/status")
    public String updateApplicationStatus(@PathVariable Long applicationId,
                                          @RequestParam String status) {
        applicationService.updateStatus(applicationId, status);
        return "Application status updated successfully.";
    }

    @GetMapping("/faculty/{facultyId}")
    public List<Application> getApplicationsByFaculty(@PathVariable Long facultyId) {
        return applicationService.getApplicationsByFaculty(facultyId);
    }

    @GetMapping("/project/{projectId}/students")
    public List<Student> getStudentsByProject(@PathVariable Long projectId) {
        return applicationService.getStudentsByProjectId(projectId);
    }

    @DeleteMapping("/{applicationId}")
    public String deleteById(@PathVariable Long applicationId) {
        return applicationService.deleteById(applicationId);
    }

    @DeleteMapping("/{applicationId}/student/{studentId}/withdraw")
    public String withdrawApplication(@PathVariable Long applicationId,
                                      @PathVariable Long studentId) {
        return applicationService.withdraw(applicationId, studentId);
    }
}
