package com.example.coderHack.model;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank
    private String userId;

    @NotBlank
    private String username;

    private int score;
    private Set<String> badges = new HashSet<>();

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public void setBadges(String badge){
        this.badges.add(badge);
    }


    // Constructors, getters, and setters
}