package com.example.coderHack.controller;

import com.example.coderHack.model.User;
import com.example.coderHack.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        Optional<User> userOptional = userService.getUserById(user.getUserId());
        if (userOptional.isPresent()){
            User user1 = userOptional.get();
            return new ResponseEntity<>(user1, HttpStatus.CONFLICT);
        }
        user.setScore(0);
        userService.registerUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateScore(@PathVariable String userId, @RequestParam int score) {
        Optional<User> userOptional = userService.updateScore(userId);
        if(score < 0 || score > 100){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setScore(score);
            updateBadges(user);
            userService.registerUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            userService.deleteByUserId(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping
    public void deleteAllUsers(){
        userService.deleteAllUsers();
    }

    private void updateBadges(User user) {
        int score = user.getScore();
        if (score >= 1 && score <= 30) {
            user.setBadges("Code Ninja");
        } else if (score > 30 && score <= 60) {
            user.setBadges("Code Champ");
        } else if (score > 60 && score <= 100) {
            user.setBadges("Code Master");
        }
    }
}
