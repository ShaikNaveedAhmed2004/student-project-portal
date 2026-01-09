package com.example.projectportal.repository;

import com.example.projectportal.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

        @Query (value = "SELECT email from users where email =:email",nativeQuery = true)
        Optional<User> findByEmail(@Param("email") String email);

        Optional<User> findByEmailAndPassword(String email,String password);

}

