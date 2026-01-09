package com.example.projectportal.repository;

import com.example.projectportal.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query(value = "SELECT m FROM messages WHERE m.applicationId =:applicationId",nativeQuery = true)
    List<Message> findByApplicationId(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT m FROM messages WHERE m.receiverId =:receiverId",nativeQuery = true)
    List<Message> findByreceiverId(@Param("receiverId") Long receiverId);

}
