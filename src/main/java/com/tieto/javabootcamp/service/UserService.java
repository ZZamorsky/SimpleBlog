package com.tieto.javabootcamp.service;

import com.tieto.javabootcamp.model.user.User;

import java.util.List;

public interface UserService {

    void createUser(String name);

    User getUser(String name);

    List<User> getUsersByName(String name);

    List<User> getAllUsers();

    boolean removeUser(String name);

    void authenticateUser();

}
