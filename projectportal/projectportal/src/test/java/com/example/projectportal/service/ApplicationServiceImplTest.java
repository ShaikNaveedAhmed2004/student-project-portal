package com.example.projectportal.service;

import com.example.projectportal.dto.ApplicationDto;
import com.example.projectportal.model.Application;
import com.example.projectportal.model.Project;
import com.example.projectportal.model.Student;
import com.example.projectportal.repository.ApplicationRepository;
import com.example.projectportal.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplicationServiceImplTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApplyForProject_Success() {
        ApplicationDto dto = new ApplicationDto(1L, 1L, "student");
        Project project = new Project();
        project.setId(1L);
        project.setDeadline(LocalDate.now().plusDays(2));
        project.setProjectCapacity(3);

        when(applicationRepository.findByStudentAndProject(1L, 1L)).thenReturn(null);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(applicationRepository.countApplicationsByStudent(1L)).thenReturn(2L);
        when(applicationRepository.countApplicationsByProject(1L)).thenReturn(1L);

        String result = applicationService.applyForProject(dto);

        assertEquals("Application submitted successfully", result);
    }

    @Test
    void testApplyForProject_AlreadyApplied() {
        ApplicationDto dto = new ApplicationDto(1L, 1L, "student");

        when(applicationRepository.findByStudentAndProject(1L, 1L)).thenReturn(new Application());

        String result = applicationService.applyForProject(dto);

        assertEquals("You have already applied to this project.", result);
    }

    @Test
    void testApplyForProject_ProjectNotFound() {
        ApplicationDto dto = new ApplicationDto(1L, 99L, "student");

        when(applicationRepository.findByStudentAndProject(1L, 99L)).thenReturn(null);
        when(projectRepository.findById(99L)).thenReturn(Optional.empty());

        String result = applicationService.applyForProject(dto);

        assertEquals("Project not found.", result);
    }

    @Test
    void testApplyForProject_DeadlinePassed() {
        ApplicationDto dto = new ApplicationDto(1L, 1L, "student");

        Project project = new Project();
        project.setDeadline(LocalDate.now().minusDays(1));

        when(applicationRepository.findByStudentAndProject(1L, 1L)).thenReturn(null);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        String result = applicationService.applyForProject(dto);

        assertEquals("Deadline has passed. You cannot apply to this project.", result);
    }

    @Test
    void testApplyForProject_InvalidRole() {
        ApplicationDto dto = new ApplicationDto(1L, 1L, "faculty");

        Project project = new Project();
        project.setDeadline(LocalDate.now().plusDays(1));

        when(applicationRepository.findByStudentAndProject(1L, 1L)).thenReturn(null);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        String result = applicationService.applyForProject(dto);

        assertEquals("Only students can apply for projects", result);
    }

    @Test
    void testApplyForProject_StudentLimitExceeded() {
        ApplicationDto dto = new ApplicationDto(1L, 1L, "student");

        Project project = new Project();
        project.setDeadline(LocalDate.now().plusDays(1));

        when(applicationRepository.findByStudentAndProject(1L, 1L)).thenReturn(null);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(applicationRepository.countApplicationsByStudent(1L)).thenReturn(5L);

        String result = applicationService.applyForProject(dto);

        assertEquals("Limit Exceeded: you can't apply for more than 5 projects", result);
    }

    @Test
    void testApplyForProject_CapacityReached() {
        ApplicationDto dto = new ApplicationDto(1L, 1L, "student");

        Project project = new Project();
        project.setDeadline(LocalDate.now().plusDays(1));
        project.setProjectCapacity(2);

        when(applicationRepository.findByStudentAndProject(1L, 1L)).thenReturn(null);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(applicationRepository.countApplicationsByStudent(1L)).thenReturn(2L);
        when(applicationRepository.countApplicationsByProject(1L)).thenReturn(2L);

        String result = applicationService.applyForProject(dto);

        assertEquals("Project Capacity Reached. You Can't Apply", result);
    }

    @Test
    void testWithdraw_Success() {
        Application application = new Application();
        application.setProjectId(2L);

        when(applicationRepository.findByStudentAndProject(1L, 2L)).thenReturn(application);

        String result = applicationService.withdraw(2L, 1L);

        assertEquals("Your Application has been withdrawn successfully", result);
    }

    @Test
    void testWithdraw_NotFound() {
        when(applicationRepository.findByStudentAndProject(1L, 99L)).thenReturn(null);

        String result = applicationService.withdraw(99L, 1L);

        assertEquals("No active application found to withdraw", result);
    }

    @Test
    void testUpdateStatus() {
        Application application = new Application();
        application.setId(1L);
        application.setStudentId(1L);
        application.setProjectId(2L);

        Project project = new Project();
        project.setFacultyId(10L);
        project.setTitle("AI Project");

        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(projectRepository.findById(2L)).thenReturn(Optional.of(project));

        applicationService.updateStatus(1L, "APPROVED");
    }

    @Test
    void testDeleteById_Exists() {
        when(applicationRepository.existsById(1L)).thenReturn(true);

        String result = applicationService.deleteById(1L);

        assertEquals("Application has been deleted", result);
    }

    @Test
    void testDeleteById_NotFound() {
        when(applicationRepository.existsById(99L)).thenReturn(false);

        String result = applicationService.deleteById(99L);

        assertEquals("Application not found", result);
    }

    @Test
    void testGetApplicationsByStudent() {
        applicationService.getApplicationsByStudent(1L);
    }

    @Test
    void testGetApplicationsByProject() {
        applicationService.getApplicationsByProject(1L);
    }

    @Test
    void testGetApplicationsByFaculty() {
        applicationService.getApplicationsByFaculty(1L);
    }

    @Test
    void testGetStudentsByProjectId() {
        applicationService.getStudentsByProjectId(1L);
    }
}
