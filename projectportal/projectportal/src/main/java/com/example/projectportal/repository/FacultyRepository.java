package com.example.projectportal.repository;

import com.example.projectportal.model.Faculty;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query(value = "SELECT f.*, u.name, u.email, u.password, u.role " +
            "FROM faculty f JOIN users u ON u.id = f.user_id " +
            "WHERE f.user_id = :userId", nativeQuery = true)
    Optional<Faculty> findByUserId(@Param("userId") long userId);


}
