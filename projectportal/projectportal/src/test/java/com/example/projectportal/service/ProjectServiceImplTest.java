package com.example.projectportal.service;

import com.example.projectportal.dto.ProjectDto;
import com.example.projectportal.model.Project;
import com.example.projectportal.repository.ApplicationRepository;
import com.example.projectportal.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject_Success() {
        ProjectDto dto = new ProjectDto("AI", "GPT", "2025-12-31", 1L, "FACULTY", 5);
        Mockito.when(projectRepository.findDuplicateProject(dto.getTitle(), dto.getDescription())).thenReturn(Optional.empty());
        String result = projectService.createProject(dto);
        assertEquals("Project Created Successfully", result);
    }

    @Test
    void testCreateProject_Duplicate() {
        ProjectDto dto = new ProjectDto("AI", "GPT", "2025-12-31", 1L, "FACULTY", 5);
        Mockito.when(projectRepository.findDuplicateProject(dto.getTitle(), dto.getDescription())).thenReturn(Optional.of(new Project()));
        String result = projectService.createProject(dto);
        assertEquals("Project already exists with same data", result);
    }

    @Test
    void testCreateProject_InvalidRole() {
        ProjectDto dto = new ProjectDto("AI", "GPT", "2025-12-31", 1L, "STUDENT", 6);
        Mockito.when(projectRepository.findDuplicateProject(dto.getTitle(), dto.getDescription())).thenReturn(Optional.empty());
        String result = projectService.createProject(dto);
        assertEquals("Only Faculty can create Projects", result);
    }

    @Test
    void testGetAllProjects() {
        Project p1 = new Project();
        Project p2 = new Project();
        Project p3 = new Project();
        Mockito.when(projectRepository.findAll()).thenReturn(List.of(p1,p2,p3));
        List<Project> result = projectService.getAllProjects();
        assertEquals(3, result.size());
    }

    @Test
    void testGetProjectsByFaculty() {
        Project project = new Project();
        Mockito.when(projectRepository.findByFacultyId(1L)).thenReturn(List.of(project));
        List<Project> result = projectService.getProjectsByFaculty(1L);
        assertEquals(1, result.size());
    }

    @Test
    void testDeleteById() {
        String result = projectService.deleteById(1L);
        assertEquals("Project Deleted Successfully", result);
    }

    @Test
    void testUpdateDetails() {
        Project project = new Project();
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        projectService.updateDetails(1L, "New Title", "New Description");
        assertEquals("New Title", project.getTitle());
        assertEquals("New Description", project.getDescription());
    }

    @Test
    void testUpdateDeadline() {
        Project project = new Project();
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        projectService.updateDeadline(1L, "2025-12-31");
        assertEquals(LocalDate.parse("2025-12-31"), project.getDeadline());
    }

    @Test
    void testUpdateCapacity_Valid() {
        Project project = new Project();
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        Mockito.when(applicationRepository.countApplicationsByProject(1L)).thenReturn(2L);
        projectService.updateCapacity(1L, 5);
        assertEquals(5, project.getProjectCapacity());
    }


    @Test
    void testSearchProjects() {
        Project project = new Project();
        Mockito.when(projectRepository.searchProjects(1L, "AI", LocalDate.parse("2025-12-31"))).thenReturn(List.of(project));
        List<Project> result = projectService.searchProjects(1L, "AI", LocalDate.parse("2025-12-31"));
        assertEquals(1, result.size());
    }
}

