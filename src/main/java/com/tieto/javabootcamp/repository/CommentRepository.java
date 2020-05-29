package com.tieto.javabootcamp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.tieto.javabootcamp.model.text.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long> {
	
	Optional<Comment> findById(Long id);

}
