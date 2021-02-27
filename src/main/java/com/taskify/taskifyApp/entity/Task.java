package com.taskify.taskifyApp.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String title;

    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<User> assignee;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Organization organization;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Task() {
    }
}
