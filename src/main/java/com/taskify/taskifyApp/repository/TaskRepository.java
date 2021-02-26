package com.taskify.taskifyApp.repository;

import com.taskify.taskifyApp.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
