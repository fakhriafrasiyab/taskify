package com.taskify.taskifyApp.service;


import com.taskify.taskifyApp.entity.Status;
import com.taskify.taskifyApp.entity.Task;
import com.taskify.taskifyApp.entity.User;
import com.taskify.taskifyApp.repository.RoleRepository;
import com.taskify.taskifyApp.repository.TaskRepository;
import com.taskify.taskifyApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ManageTaskService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private TaskRepository taskRepository;

    ManageTaskService(UserRepository userRepository, RoleRepository roleRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.taskRepository = taskRepository;
    }

    public void store(String title, String description, LocalDateTime deadline, Status status, String userEmail) {
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (user.isPresent()) {
            Task task = new Task();
            task.setTitle(title);
            task.setDescription(description);
            task.setDeadline(deadline);
            task.setStatus(status);
            taskRepository.save(task);
        }
    }

}
