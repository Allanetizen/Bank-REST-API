package com.example.banking.User;

import org.springframework.stereotype.Indexed;

import javax.persistence.*;


public class User {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    private Long Id;
    private String username;
    private String UserId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
    }

    public User(String username, String userId, String password, String firstName, String lastName, String email) {
        this.username = username;
        UserId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(Long id, String username, String userId, String password, String firstName, String lastName, String email) {
        Id = id;
        this.username = username;
        UserId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return UserId;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", UserId='" + UserId + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
