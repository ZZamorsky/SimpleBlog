package com.tieto.javabootcamp.repository;

import java.util.Optional;
import com.tieto.javabootcamp.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByName(String name);
	
	void deleteById(Long id);
	

}
