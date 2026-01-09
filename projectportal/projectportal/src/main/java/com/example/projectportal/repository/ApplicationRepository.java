package com.example.projectportal.repository;

import com.example.projectportal.model.Application;
import java.util.List;

import com.example.projectportal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "SELECT * FROM application WHERE student_id = :studentId", nativeQuery = true)
    List<Application> findByStudentId(@Param("studentId") Long studentId);

    @Query(value = "SELECT * FROM application WHERE project_id = :projectId", nativeQuery = true)
    List<Application> findByProjectId(@Param("projectId") Long projectId);

    @Query(value = "SELECT * FROM application WHERE project_id = :projectId AND student_id = :studentId", nativeQuery = true)
    Application findByStudentAndProject(@Param("studentId") Long studentId,
                                        @Param("projectId") Long projectId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE application SET status = :status WHERE id = :id", nativeQuery = true)
    void updateApplicationStatus(@Param("id") Long id, @Param("status") String status);

    @Query(value = "SELECT a.* FROM application a JOIN project p ON a.project_id = p.id WHERE p.faculty_id = :facultyId", nativeQuery = true)
    List<Application> findByFacultyId(@Param("facultyId") Long facultyId);

    @Query(value = "SELECT s.* FROM student s JOIN application a ON s.id = a.student_id WHERE a.project_id = :projectId", nativeQuery = true)
    List<Student> findStudentsByProjectId(@Param("projectId") Long projectId);

    @Query(value = "SELECT COUNT(*) FROM application WHERE student_id = :studentId", nativeQuery = true)
    long countApplicationsByStudent(@Param("studentId") Long studentId);

    @Query(value = "SELECT COUNT(*) FROM application WHERE project_id = :projectId", nativeQuery = true)
    long countApplicationsByProject(@Param("projectId") Long projectId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM application WHERE id = :id AND student_id = :studentId", nativeQuery = true)
    void Withdrawapplication(@Param("id") Long id, @Param("studentId") Long studentId);
}
