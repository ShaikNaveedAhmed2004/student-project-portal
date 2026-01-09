package com.example.projectportal.service;

import com.example.projectportal.service.MessageService;
import com.example.projectportal.dto.ApplicationDto;
import com.example.projectportal.model.Application;
import com.example.projectportal.model.Project;
import com.example.projectportal.model.Student;
import com.example.projectportal.repository.ApplicationRepository;
import com.example.projectportal.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MessageService messageService;

    @Override
    public String applyForProject(ApplicationDto dto) {
        Optional<Application> existing = Optional.ofNullable(
                applicationRepository.findByStudentAndProject(dto.getStudentId(), dto.getProjectId())
        );

        if (existing.isPresent()) {
            return "You have already applied to this project.";
        }

        Project project = projectRepository.findById(dto.getProjectId()).orElse(null);
        if (project == null) {
            return "Project not found.";
        }

        if (project.getDeadline().isBefore(LocalDate.now())) {
            return "Deadline has passed. You cannot apply to this project.";
        }

        if (!"student".equalsIgnoreCase(dto.getRole())) {
            return "Only students can apply for projects";
        }

        int count = Math.toIntExact(applicationRepository.countApplicationsByStudent(dto.getStudentId()));
        if (count >= 5) {
            return "Limit Exceeded: you can't apply for more than 5 projects";
        }

        int appliedCount = Math.toIntExact(applicationRepository.countApplicationsByProject(dto.getProjectId()));
        if (appliedCount >= project.getProjectCapacity()) {
            return "Project Capacity Reached. You Can't Apply";
        }

        Application application = new Application();
        application.setStudentId(dto.getStudentId());
        application.setProjectId(dto.getProjectId());
        application.setStatus("PENDING");
        application.setAppliedAt(LocalDate.now());

        applicationRepository.save(application);
        return "Application submitted successfully";
    }

    @Override
    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    @Override
    public List<Application> getApplicationsByProject(Long projectId) {
        return applicationRepository.findByProjectId(projectId);
    }

    @Override
    public void updateStatus(Long applicationId, String status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        applicationRepository.save(application);

        Long studentId = application.getStudentId();
        Project project = projectRepository.findById(application.getProjectId()).orElseThrow();
        Long facultyId = project.getFacultyId();

        String content = "your application for project" + project.getTitle() + "Has been updated to" + status;

        messageService.sendMessage(applicationId,facultyId,studentId,content);
    }

    @Override
    public List<Application> getApplicationsByFaculty(Long facultyId) {
        return applicationRepository.findByFacultyId(facultyId);
    }

    @Override
    public List<Student> getStudentsByProjectId(Long projectId) {
        return applicationRepository.findStudentsByProjectId(projectId);
    }

    @Override
    public String deleteById(Long applicationId) {
        if (!applicationRepository.existsById(applicationId)) {
            return "Application not found";
        }
        applicationRepository.deleteById(applicationId);
        return "Application has been deleted";
    }

    @Override
    public String withdraw(Long applicationId, Long studentId) {
        Application existing = applicationRepository.findByStudentAndProject(studentId, applicationId);
        if (existing == null) {
            return "No active application found to withdraw";
        }
        applicationRepository.Withdrawapplication(applicationId, studentId);
        return "Your Application has been withdrawn successfully";
    }
}
