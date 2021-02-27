package com.taskify.taskifyApp.controllers;

import com.taskify.taskifyApp.payload.request.TaskStoreRequest;
import com.taskify.taskifyApp.payload.request.UserStoreRequest;
import com.taskify.taskifyApp.payload.response.MessageResponse;
import com.taskify.taskifyApp.service.ManageTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tasks")
public class ManageTaskController {

    private ManageTaskService manageTaskService;

    public ManageTaskController(ManageTaskService manageTaskService) {
        this.manageTaskService = manageTaskService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAndAssignTask(@Valid @RequestBody TaskStoreRequest taskStoreRequest, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        manageTaskService.createAndAssignTask(taskStoreRequest.getTitle(), taskStoreRequest.getDescription(),
                taskStoreRequest.getDeadline(), taskStoreRequest.getStatus(),
                taskStoreRequest.getAssignee(), userDetails.getUsername());
        return ResponseEntity.ok(new MessageResponse("User created and assign task successfully"));
    }
    @GetMapping
    public ResponseEntity<?> listTasks(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(manageTaskService.getList(userDetails.getUsername()));
    }
}
