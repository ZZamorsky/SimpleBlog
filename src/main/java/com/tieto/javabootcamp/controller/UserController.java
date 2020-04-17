package com.tieto.javabootcamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tieto.javabootcamp.model.user.User;
import com.tieto.javabootcamp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	
	public UserController(@Autowired UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(params={"userName"})
	public List<User> getUsersByName(
			@RequestParam(name="userName", required=true) String name) {
		return userService.getUsersByName(name);
	}
	
	@GetMapping("/{userName}")
	public User getUser(@PathVariable("userName") String name) {
		return userService.getUser(name);
	}
	
	@CrossOrigin("*")
	@PostMapping()
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@DeleteMapping("/{userId}")
	public void delete(@PathVariable Long userId) {
		userService.removeUser(userId);
	}
}
