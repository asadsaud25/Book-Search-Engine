package com.h2.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Entity representing a user in the system.
 * Each user has a unique username and email, a hashed password, and a role (admin or regular user).
 * The creation timestamp is automatically set when the user is created.
 */
@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid-gen")
    @GenericGenerator(name = "uuid-gen", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id; // Unique identifier for the user

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username; // Username of the user

    @Column(name = "password_hash", nullable = false, length = 255)
    private String password; // Hashed password

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email; // Email address

    @Column(name = "isadmin", nullable = false)
    private Boolean isAdmin; // Flag indicating if the user is an admin

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt; // Timestamp when the user was created
}