package com.taskify.taskifyApp.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String name;
    private String phoneNumber;
    private String address;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Organization(String name, String phoneNumber, String address, User user) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.user = user;
    }

    public Organization() {
    }
}
