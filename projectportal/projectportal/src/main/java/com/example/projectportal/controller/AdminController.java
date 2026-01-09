package com.example.projectportal.controller;

import com.example.projectportal.model.User;
import com.example.projectportal.model.Project;
import com.example.projectportal.model.Application;
import com.example.projectportal.repository.ApplicationRepository;
import com.example.projectportal.repository.ProjectRepository;
import com.example.projectportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return "User deleted Successfully";
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    @DeleteMapping("/projects/{id}")
    public String deleteProjects(@PathVariable Long id){
        projectRepository.deleteById(id);
        return "Project deleted Successfully";
    }

    @GetMapping("/applications")
    public List<Application> getApplications(){
        return applicationRepository.findAll();
    }

    public String deleteApplications(@PathVariable Long id){
        applicationRepository.deleteById(id);
        return "Application Has Been Deleted";
    }

    @GetMapping("/count")
    public String getDashboard(){
        Long userCount = userRepository.count();
        Long projectsCount = projectRepository.count();
        Long applicationCount = applicationRepository.count();

        return "Count: " + "\n" +"users:"+ userCount + "\n"+ "projects:" + projectsCount+ "\n" + "applications:" +applicationCount;
    }

}
