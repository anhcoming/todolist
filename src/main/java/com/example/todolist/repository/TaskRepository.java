package com.example.todolist.repository;

import com.example.todolist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    boolean existsByTitle(String title);

    @Query(value = "SELECT * FROM todolist.task WHERE title LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<TaskEntity> searchByTitle(@Param("keyword") String keyword);

}

