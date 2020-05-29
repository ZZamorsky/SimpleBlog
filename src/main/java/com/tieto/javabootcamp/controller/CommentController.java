package com.tieto.javabootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tieto.javabootcamp.model.text.Comment;
import com.tieto.javabootcamp.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired CommentService commentService;
	
	@GetMapping public Iterable<Comment> get() { 
		return commentService.listComments();
	}
	
	@PostMapping public Comment post(@RequestBody Comment comment, @AuthenticationPrincipal User user) {
		return commentService.saveComment(comment, user);
	}
	@DeleteMapping public void remove(@RequestBody Comment comment, @AuthenticationPrincipal User user) {
		commentService.removeComment(comment, user);
	}
	@PutMapping public void putComment(@RequestBody Comment comment, @AuthenticationPrincipal User user) {
		commentService.updateComment(comment, user);
	}

}
