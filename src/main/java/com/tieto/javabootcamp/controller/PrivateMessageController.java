package com.tieto.javabootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.model.text.Comment;
import com.tieto.javabootcamp.model.text.PrivateMessage;
import com.tieto.javabootcamp.service.ArticleService;
import com.tieto.javabootcamp.service.CommentService;
import com.tieto.javabootcamp.service.PrivateMessageService;

@RestController
@RequestMapping("/api/private-messages")
public class PrivateMessageController {

	@Autowired PrivateMessageService privateMessageService;
	
	@GetMapping public Iterable<PrivateMessage> get() { 
		return privateMessageService.listArticles();
	}
	
	@PostMapping public PrivateMessage post(@RequestBody PrivateMessage article) {
		return privateMessageService.saveAritcle(article);
	}
}
