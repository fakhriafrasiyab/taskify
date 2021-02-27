package com.taskify.taskifyApp.dto;

import com.taskify.taskifyApp.entity.Organization;
import com.taskify.taskifyApp.entity.Status;
import com.taskify.taskifyApp.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskDTO {
    private long id;
    private String title;
    private String description;

    private LocalDateTime deadline;
    private LocalDateTime createdAt;

    private List<UserDTO> assignee;
    private Status status;

    public TaskDTO(long id, String title, String description, LocalDateTime deadline, LocalDateTime createdAt, List<UserDTO> assignee, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.assignee = assignee;
        this.status = status;
    }
}
