package com.taskify.taskifyApp.service;


import com.taskify.taskifyApp.dto.TaskDTO;
import com.taskify.taskifyApp.dto.UserDTO;
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
    private EmailService emailService;

    ManageTaskService(UserRepository userRepository, RoleRepository roleRepository, TaskRepository taskRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.taskRepository = taskRepository;
        this.emailService = emailService;
    }

    public void createAndAssignTask(String title, String description, LocalDateTime deadline, Status status, List<Integer> assignee, String userEmail) {
        Optional<User> user = userRepository.findByEmail(userEmail);
        Task task = new Task();
        if (user.isPresent()) {
            task.setTitle(title);
            task.setDescription(description);
            task.setDeadline(deadline);
            task.setStatus(status);
            task.setOrganization(user.get().getOrganization());
            taskRepository.save(task);
        }
        List<User> users = new ArrayList<>();
        if (!assignee.isEmpty()) {
            for (Integer id : assignee) {
                User user1 = new User();
                user1.setId(id);
                users.add(user1);
                Optional<User> mailUser = userRepository.findById(id);

                mailUser.ifPresent(value -> emailService.sendSimpleMessage(value.getEmail(), "New Task", "Yeni Task assign olundu,gozun aydin"));
            }
            task.setAssignee(users);
            taskRepository.save(task);
        }
    }

    public List<TaskDTO> getList(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        List<TaskDTO> taskDTOS = new ArrayList<>();
        if (user.isPresent()) {
            List<Task> tasks = taskRepository.getByOrganization(user.get().getOrganization());
            for (Task task : tasks) {
                List<UserDTO> list = new ArrayList<>();
                for (User user1 : task.getAssignee()) {
                    list.add(new UserDTO(user1.getId(), user1.getName(), user1.getSurname(), user1.getEmail()));
                }
                taskDTOS.add(new TaskDTO(
                        task.getId(), task.getTitle(), task.getDescription(),
                        task.getDeadline(), task.getCreatedAt(),
                        list, task.getStatus()
                ));
            }
        }
        return taskDTOS;
    }
}


