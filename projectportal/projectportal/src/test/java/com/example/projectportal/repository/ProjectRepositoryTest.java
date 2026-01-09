package com.example.projectportal.repository;

import com.example.projectportal.dto.ProjectDto;
import com.example.projectportal.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class ProjectRepositoryTest {

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByFacultyId() {
        Project project = new Project("ML","regression", LocalDate.of(2026,9,7),1l,5);
        Mockito.when(projectRepository.findByFacultyId(project.getFacultyId())).thenReturn(List.of(project));
        assertEquals(project.getFacultyId(),1);
    }

    @Test
    void findDuplicateProject() {
        ProjectDto dto = new ProjectDto("ML","regression", "2026-09-07",1l,"faculty",5);
        Mockito.when(projectRepository.findDuplicateProject(dto.getTitle(), dto.getDescription())).thenReturn(Optional.of(new Project()));
        assertEquals("ML", dto.getTitle());
        assertEquals("regression", dto.getDescription());

    }

    @Test
    void updateProjectDetails() {

    }

    @Test
    void updateDeadline() {
    }

    @Test
    void searchProjects() {
    }
}