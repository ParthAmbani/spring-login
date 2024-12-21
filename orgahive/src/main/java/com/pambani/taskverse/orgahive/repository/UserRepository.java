package com.pambani.taskverse.orgahive.repository;

import com.pambani.taskverse.orgahive.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    // Method to return the first matching user based on email
    default User findFirstByEmail(String email) {
        List<User> users = findAllByEmail(email);
        return users.isEmpty() ? null : users.get(0);
    }

    // Custom method to find users by email, sorted by _id to ensure the first one is returned
    List<User> findAllByEmail(String email);

}