package com.tieto.javabootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@Secured("ROLE_ADMIN")
	@GetMapping()
	public Iterable<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{userName}")
	public User getUser(@PathVariable("userName") String name) {
		return userService.getUser(name);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping()
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{userName}")
	public void delete(@PathVariable String userName) {
		userService.removeUser(userName);
	}

}
