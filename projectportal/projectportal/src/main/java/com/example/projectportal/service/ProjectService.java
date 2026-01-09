package com.example.projectportal.service;

import com.example.projectportal.dto.ProjectDto;
import com.example.projectportal.model.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectService {
    String createProject(ProjectDto dto);
    List<Project> getAllProjects();
    List<Project> getProjectsByFaculty(Long facultyId);
    String deleteById(Long projectId);
    void updateDetails(Long projectId, String title, String description);
    void updateDeadline(Long projectId, String deadline);
    void updateCapacity(Long projectId, int capacity);
    List<Project> searchProjects(Long facultyId, String title, LocalDate deadline);

}
