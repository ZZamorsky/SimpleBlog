package com.tieto.javabootcamp.repository;

import org.springframework.data.repository.CrudRepository;

import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.model.text.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
