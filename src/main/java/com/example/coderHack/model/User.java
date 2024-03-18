package com.example.coderHack.model;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.Set;

@Data
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

    public void setBadges(String badge){
        this.badges.add(badge);
    }


    // Constructors, getters, and setters
}