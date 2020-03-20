package com.tieto.javabootcamp.repository;

import java.util.List;
import java.util.Optional;

import com.tieto.javabootcamp.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByName(String name);
	List<User> findAllByName(String name);
}
