package com.tieto.javabootcamp.application;

import com.tieto.javabootcamp.model.user.User;
import com.tieto.javabootcamp.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EntityScan("com.tieto.javabootcamp.model.*")
public class DatabaseApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DatabaseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		// save a few users
		userRepository.save(new User("Pavel"));
		userRepository.save(new User("David"));
		userRepository.save(new User("Honza"));
		userRepository.save(new User("Tereza"));
		userRepository.save(new User("Jana"));

		// fetch all users
		log.info("Users found with findAll():");
		log.info("-------------------------------");
		for (User user : userRepository.findAll()) {
			log.info(user.toString());
		}
		log.info("");

		// fetch an individual user by name
		User user = userRepository.findByName("Pavel").get();
		log.info("User found with findByName(\"Pavel\"):");
		log.info("--------------------------------");
		log.info(user.toString());
		log.info("");

	}

}
