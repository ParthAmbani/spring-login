package com.pambani.taskverse.orgahive.service;

import com.pambani.taskverse.orgahive.dto.User;
import com.pambani.taskverse.orgahive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }
}
