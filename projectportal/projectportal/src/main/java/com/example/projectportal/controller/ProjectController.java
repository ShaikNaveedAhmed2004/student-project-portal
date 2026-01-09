package com.example.projectportal.controller;

import com.example.projectportal.dto.ProjectDto;
import com.example.projectportal.model.Project;
import com.example.projectportal.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public String createProject(@RequestBody ProjectDto dto) {
        return projectService.createProject(dto);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/faculty/{faculty_id}")
    public List<Project> getProjectsByFaculty(@PathVariable Long faculty_id) {
        return projectService.getProjectsByFaculty(faculty_id);
    }

    @DeleteMapping("/{projectId}")
    public String deleteById(@PathVariable Long projectId) {
        return projectService.deleteById(projectId);
    }

    @PutMapping("/{projectId}/details")
    public String updateProjectDetails(@PathVariable Long projectId,
                                       @RequestParam String title,
                                       @RequestParam String description) {
        projectService.updateDetails(projectId, title, description);
        return "Project Details Updated Successfully";
    }

    @PatchMapping("/{projectId}/deadline")
    public String updateDeadline(@PathVariable Long projectId,
                                 @RequestParam String deadline) {
        projectService.updateDeadline(projectId, deadline);
        return "Project Deadline has been Changed";
    }

    @PatchMapping("{projectId}/capacity")
    public String updateCapacity(@PathVariable Long projectId,
                                 @RequestParam int capacity) {
        projectService.updateCapacity(projectId, capacity);
        return "Project Capacity has been Updated Successfully";
    }

    @GetMapping("/search")
    public List<Project> searchProjects(
            @RequestParam Long facultyId,
            @RequestParam String title,
            @RequestParam String deadline
    ) {
        LocalDate parsedDeadline = LocalDate.parse(deadline);
        return projectService.searchProjects(facultyId, title, parsedDeadline);
    }
}
