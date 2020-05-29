package com.tieto.javabootcamp.service;

import com.tieto.javabootcamp.model.user.User;


public interface UserService {

    User createUser(User name);

    User getUser(String name);

    Iterable<User> getAllUsers();

	boolean deleteUser(Long id);
	
	User updateUser(User user);

	boolean verifyUser(String name);

}
