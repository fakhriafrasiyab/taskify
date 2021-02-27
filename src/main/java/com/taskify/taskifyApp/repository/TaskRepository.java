package com.taskify.taskifyApp.repository;

import com.taskify.taskifyApp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
