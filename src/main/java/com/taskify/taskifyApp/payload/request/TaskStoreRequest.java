package com.taskify.taskifyApp.payload.request;

import com.taskify.taskifyApp.entity.Status;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class TaskStoreRequest {
    private String title;
    private String description;

    private LocalDateTime deadline;

    private Status status;
}
