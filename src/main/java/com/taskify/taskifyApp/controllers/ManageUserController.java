package com.taskify.taskifyApp.controllers;

import com.taskify.taskifyApp.entity.User;
import com.taskify.taskifyApp.payload.request.SignupRequest;
import com.taskify.taskifyApp.payload.request.UserStoreRequest;
import com.taskify.taskifyApp.payload.response.MessageResponse;
import com.taskify.taskifyApp.service.ManageUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class ManageUserController {

    private ManageUserService manageUserService;

    public ManageUserController(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> store(@Valid @RequestBody UserStoreRequest userStoreRequest, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        manageUserService.store(userStoreRequest.getName(), userStoreRequest.getSurname(),
                userStoreRequest.getEmail(), userDetails.getUsername());
        return ResponseEntity.ok(new MessageResponse("Admin created default user successfully"));
    }
}
