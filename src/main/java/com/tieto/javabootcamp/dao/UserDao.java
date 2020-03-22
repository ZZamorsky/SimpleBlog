package com.tieto.javabootcamp.dao;

import com.tieto.javabootcamp.exception.DatabaseException;
import com.tieto.javabootcamp.model.user.User;

import java.util.List;

public interface UserDao {

    User saveUser(User user) throws DatabaseException;

    Long deleteUser(String name) throws DatabaseException;

    User getUser(String name) throws DatabaseException;

    List<User> getUsersByName(String name);

    List<User> getAllUsers();

}
