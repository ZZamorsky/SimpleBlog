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
import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.service.ArticleService;


@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@GetMapping public Iterable<Article> get() { 
		return articleService.listArticles();
	}
	
	@GetMapping("/pageable")
	public Iterable<Article> getThree() {
		return articleService.ListThreeArticle();
	}
	
	@PostMapping public Article post(@RequestBody Article article, @AuthenticationPrincipal User user) {
		return articleService.saveArticle(article, user);
	}
	
		
	@DeleteMapping public void remove(@RequestBody Article article, @AuthenticationPrincipal User user) {
		articleService.removeArticle(article, user);	
	
	}
	@PutMapping public void putArticle(@RequestBody Article article, @AuthenticationPrincipal User user) {
		articleService.updateArticle(article, user);
	}
	
}
