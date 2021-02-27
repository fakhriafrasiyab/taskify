package com.taskify.taskifyApp.service;


import com.taskify.taskifyApp.entity.Status;
import com.taskify.taskifyApp.entity.Task;
import com.taskify.taskifyApp.entity.User;
import com.taskify.taskifyApp.repository.RoleRepository;
import com.taskify.taskifyApp.repository.TaskRepository;
import com.taskify.taskifyApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public void createAndAssignTask(String title, String description, LocalDateTime deadline, Status status, List<Integer> assignee, String userEmail) {
        Optional<User> user = userRepository.findByEmail(userEmail);
        Task task = new Task();
        if (user.isPresent()) {
            task.setTitle(title);
            task.setDescription(description);
            task.setDeadline(deadline);
            task.setStatus(status);
            taskRepository.save(task);
        }
        List<User> users = new ArrayList<>();
        if (!assignee.isEmpty()) {
            for (int i = 0; i < assignee.size(); i++) {
                User user1 = new User();
                user1.setId(assignee.get(i));
                users.add(user1);
            }
            task.setAssignee(users);
            taskRepository.save(task);
        }
    }
}


