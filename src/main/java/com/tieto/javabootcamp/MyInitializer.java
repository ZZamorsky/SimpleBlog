package com.tieto.javabootcamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tieto.javabootcamp.model.user.Role;
import com.tieto.javabootcamp.model.user.User;
import com.tieto.javabootcamp.repository.RoleRepository;
import com.tieto.javabootcamp.service.UserService;

@Component
public class MyInitializer implements InitializingBean  {

	private static final Logger log = LoggerFactory.getLogger(MyInitializer.class);
	
	@Autowired UserService userService;
	@Autowired RoleRepository roleRepository;
	

	@Override
	public void afterPropertiesSet() throws Exception {
		
		Role userRole = new Role();
		userRole.setName("USER");
		roleRepository.save(userRole);
		Role adminRole = new Role();
		adminRole.setName("ADMIN");
		roleRepository.save(adminRole);
		
		userService.createUser(new User("Anicka", "anicka", roleRepository.findAll()));
		userService.createUser(new User("Standa", "standa", roleRepository.findByName("USER")));
		
		if (!userService.verifyUser("administrator")) {
			userService.createUser(new User("administrator", "admin", roleRepository.findByName("ADMIN")));
			} 

		

		// fetch all users
		log.info("Users found with getAllUsers():");
		log.info("-------------------------------");
		log.info(userService.getAllUsers().toString());
		log.info("");		
	}
}
