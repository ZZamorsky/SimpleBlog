package com.tieto.javabootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		return commentService.listArticles();
	}
	
	@PostMapping public Comment post(@RequestBody Comment article, @AuthenticationPrincipal User user) {
		return commentService.saveArticle(article);
	}
}
