package com.tieto.javabootcamp.service;

import com.tieto.javabootcamp.model.user.User;


public interface UserService {

    User createUser(User name);

    User getUser(String name);

    Iterable<User> getAllUsers();

	boolean removeUser(String name);
	
	User updateUser(User name);

}
