package com.tieto.javabootcamp.service;

import org.springframework.security.core.userdetails.User;
import com.tieto.javabootcamp.model.text.Comment;

public interface CommentService {
	
	public Iterable<Comment> listComments();

	public Comment saveComment(Comment comment, User user);

	public void removeComment(Comment comment, User user);

	public void updateComment(Comment comment, User user);	

}
