package com.taskify.taskifyApp.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String name;

    private String password;

    private String email;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Organization organization;

    @Column(name = "created_at",nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
