package com.tieto.javabootcamp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.service.ArticleService;
import com.tieto.javabootcamp.service.UserService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	@Autowired ArticleService articleService;
	
	@GetMapping public Iterable<Article> get() { 
		return articleService.listArticles();
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping public Article post(@RequestBody Article article, @AuthenticationPrincipal User user) {
		return articleService.saveAritcle(article, user);
	}
}
