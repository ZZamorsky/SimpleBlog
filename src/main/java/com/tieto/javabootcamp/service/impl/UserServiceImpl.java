package com.tieto.javabootcamp.service.impl;

import com.tieto.javabootcamp.exception.BadRequestException;
import com.tieto.javabootcamp.exception.NotFoundException;
import com.tieto.javabootcamp.model.user.User;
import com.tieto.javabootcamp.repository.UserRepository;
import com.tieto.javabootcamp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    @SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

   
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
    	if (userRepository.findByName(user.getName()).isEmpty()) {
    		user.setPassword(passwordEncoder.encode(user.getPassword()));
    		return userRepository.save(user);}
    	else throw new BadRequestException("Username already exists");}   	
          
    @Override
    public User getUser(String name) {
		return userRepository.findByName(name)
					.orElseThrow(() -> new NotFoundException("User with supplied id not found"));

    }

    @Override
    public boolean removeUser(String name) {
    	boolean isRemoved = false;
    	if (userRepository.findByName(name).isPresent()) {
    		userRepository.deleteByName(name);
    		isRemoved = true;
    	} else {
    		throw new NotFoundException("User with supplied name does not exist");}
    	return isRemoved;
    	}
    
    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

	@Override
	public User updateUser(User name) {
		// TODO Auto-generated method stub
		return null;
	}

}
