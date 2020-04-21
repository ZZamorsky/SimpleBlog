package com.tieto.javabootcamp.model.user;

import java.time.LocalDateTime;
import java.util.Set;

public class RegisteredUser extends User {

    private String name;
    private String surname;
    private String password;
    private String email;
    private LocalDateTime registeredSince;

    public RegisteredUser(String nickname, String name, String surname, String email,String password) {
        super(nickname, password, Set.of());
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.registeredSince =  LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegisteredSince() {
        return registeredSince;
    }

    public String getPassword() {
        return password;
    }
}