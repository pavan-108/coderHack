package com.example.coderHack.service;

import com.example.coderHack.model.User;
import com.example.coderHack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateScore(String userId) {
        return userRepository.findByUserId(userId);
    }

    public void deleteByUserId(String userId) {
        userRepository.deleteByUserId(userId);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
