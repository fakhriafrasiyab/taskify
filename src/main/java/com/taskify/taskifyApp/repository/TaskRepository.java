package com.taskify.taskifyApp.repository;

import com.taskify.taskifyApp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("from Task where organization=:id")
    public List<Task> getByOrganizationId(long id);
}
