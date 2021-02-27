package com.taskify.taskifyApp.payload.request;

import com.taskify.taskifyApp.entity.Status;
import com.taskify.taskifyApp.entity.User;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskStoreRequest {
    private String title;
    private String description;

    private LocalDateTime deadline;

    private Status status;

    private List<Integer> assignee;


}
