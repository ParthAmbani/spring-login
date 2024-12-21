package com.pambani.taskverse.orgahive.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ticket-user")
public class User {
    @Id
    private String id;

    private String email;

    private String password;

    // Constructors
    public User() {
        // Default constructor
    }

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString for debugging
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
