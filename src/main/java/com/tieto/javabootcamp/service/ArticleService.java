package com.tieto.javabootcamp.service;

import org.springframework.security.core.userdetails.User;
import com.tieto.javabootcamp.model.text.Article;

public interface ArticleService {
	
	public Article saveArticle(Article article, User user);
	
	public Iterable<Article> listArticles();
	
	public void removeArticle(Article article, User user);
	
	public void updateArticle(Article article, User user);

	public Iterable<Article> ListThreeArticle();
}
