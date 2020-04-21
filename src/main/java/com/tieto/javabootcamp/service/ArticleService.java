package com.tieto.javabootcamp.service;

import org.springframework.security.core.userdetails.User;

import com.tieto.javabootcamp.model.text.Article;

public interface ArticleService {
	
	public Article saveAritcle(Article article, User user);
	public Iterable<Article> listArticles();

}
