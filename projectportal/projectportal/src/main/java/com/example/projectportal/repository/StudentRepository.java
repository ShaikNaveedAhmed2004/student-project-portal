package com.example.projectportal.repository;

import com.example.projectportal.model.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

     @Query(value = "SELECT s.*, u.name, u.email, u.password, u.role " +
             "FROM student s JOIN users u ON u.id = s.user_id " +
             "WHERE s.user_id = :userId", nativeQuery = true)
     Optional<Student> findByUserId(@Param("userId") long userId);


}
