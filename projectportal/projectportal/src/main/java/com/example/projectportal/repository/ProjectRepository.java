package com.example.projectportal.repository;

import com.example.projectportal.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT * FROM project WHERE faculty_id = :facultyId", nativeQuery = true)
    List<Project> findByFacultyId(@Param("facultyId") Long facultyId);

    @Query(value = "SELECT * FROM project WHERE title = :title AND description = :description", nativeQuery = true)
    Optional<Project> findDuplicateProject(@Param("title") String title,
                                           @Param("description") String description);

    @Modifying
    @Transactional
    @Query(value = "UPDATE project SET title = :title, description = :description WHERE id = :id", nativeQuery = true)
    int updateProjectDetails(@Param("id") Long id,
                             @Param("title") String title,
                             @Param("description") String description);

    @Modifying
    @Transactional
    @Query(value = "UPDATE project SET deadline = :deadline WHERE id = :id", nativeQuery = true)
    int updateDeadline(@Param("id") Long id, @Param("deadline") LocalDate deadline);

    @Query(value = "SELECT p FROM Project p WHERE " + "(:facultyId IS NULL OR p.facultyId = :facultyId) AND " +
            "(:title IS NULL OR p.title LIKE %:title%) AND " + "(:deadline IS NULL OR p.deadline <= :deadline)",nativeQuery = true)
    List<Project> searchProjects(
            @Param("facultyId") Long facultyId,
            @Param("title") String title,
            @Param("deadline") LocalDate deadline
    );

}
