package com.tieto.javabootcamp.service.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.tieto.javabootcamp.Component.AccessRights;
import com.tieto.javabootcamp.exception.NoAccessException;
import com.tieto.javabootcamp.exception.NotFoundException;
import com.tieto.javabootcamp.model.text.Comment;
import com.tieto.javabootcamp.repository.CommentRepository;
import com.tieto.javabootcamp.repository.UserRepository;
import com.tieto.javabootcamp.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private AccessRights accessRights;
	
	@Override
	public Iterable<Comment> listComments() {
		return commentRepository.findAll();
	}

	@Override
	public Comment saveComment(Comment comment, User user) {
		comment.setCreatedAt(LocalDateTime.now());
		comment.setAuthor(
				userRepository.findByName(user.getUsername())
					.orElseThrow(() -> new NotFoundException("Author with supplied name not found"))
			);
		comment.setArticleID(comment.getId());
		return commentRepository.save(comment);		
	}

	@Override
	public void removeComment(Comment comment, User user) {
		if (accessRights.isApproved(comment, user)) {
    		if (commentRepository.findById(comment.getId()).isPresent()) {
    			commentRepository.deleteById(comment.getId());
    		} 
    		else {
    		throw new NotFoundException("Article with supplied id does not exist");
    		}
       	}
    	else throw new NoAccessException("No permission for this operation");		
	}
	
	@Override
	public void updateComment(Comment comment, User user) {
		Comment dbValue = commentRepository.findById(comment.getId())
				.orElseThrow(() -> new NotFoundException("Comment with supplied id not found"));
		if (accessRights.isApproved(dbValue, user)) {
		dbValue.setContent(comment.getContent());
		commentRepository.save(dbValue);
		}
		else throw new NoAccessException("No permission for this operation");
	}

}
