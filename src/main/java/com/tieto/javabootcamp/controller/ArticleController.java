package com.tieto.javabootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	@Autowired ArticleService articleService;
	
	@GetMapping public Iterable<Article> get() { 
		return articleService.listArticles();
	}
	
	@PostMapping public Article post(@RequestBody Article article) {
		return articleService.saveAritcle(article);
	}
}
