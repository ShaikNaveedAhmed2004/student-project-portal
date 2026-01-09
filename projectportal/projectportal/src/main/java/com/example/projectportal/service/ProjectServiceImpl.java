package com.example.projectportal.service;

import com.example.projectportal.dto.ProjectDto;
import com.example.projectportal.model.Project;
import com.example.projectportal.repository.ApplicationRepository;
import com.example.projectportal.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public String createProject(ProjectDto dto) {
        try {
            Optional<Project> existing = projectRepository.findDuplicateProject(
                    dto.getTitle(), dto.getDescription());

            if (existing.isPresent()) {
                return "Project already exists with same data";
            }

            if (!"FACULTY".equalsIgnoreCase(dto.getRole())) {
                return "Only Faculty can create Projects";
            }

            Project project = new Project();
            project.setTitle(dto.getTitle());
            project.setDescription(dto.getDescription());
            project.setDeadline(LocalDate.parse(dto.getDeadline()));
            project.setFacultyId(dto.getFacultyId());
            project.setProjectCapacity(dto.getProjectCapacity());

            projectRepository.save(project);
            return "Project Created Successfully";

        } catch (Exception e) {
            return "Error Creating Project: " + e.getMessage();
        }
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> getProjectsByFaculty(Long facultyId) {
        return projectRepository.findByFacultyId(facultyId);
    }

    @Override
    public String deleteById(Long projectId) {
        projectRepository.deleteById(projectId);
        return "Project Deleted Successfully";
    }

    @Override
    public void updateDetails(Long projectId, String title, String description) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setTitle(title);
        project.setDescription(description);
        projectRepository.save(project);
    }

    @Override
    public void updateDeadline(Long projectId, String deadline) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setDeadline(LocalDate.parse(deadline));
        projectRepository.save(project);
    }

    @Override
    public void updateCapacity(Long projectId, int capacity) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        int appliedCount = Math.toIntExact(applicationRepository.countApplicationsByProject(projectId));

        if (capacity < appliedCount) {
            throw new RuntimeException("Cannot reduce capacity below already applied students (" + appliedCount + ")");
        }
        project.setProjectCapacity(capacity);
        projectRepository.save(project);
    }

    @Override
    public List<Project> searchProjects(Long facultyId, String title, LocalDate deadline) {
        return projectRepository.searchProjects(facultyId, title, deadline);
    }

}
