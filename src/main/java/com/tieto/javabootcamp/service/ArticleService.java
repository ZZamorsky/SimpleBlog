package com.tieto.javabootcamp.service;

import com.tieto.javabootcamp.model.text.Article;

public interface ArticleService {
	
	public Article saveAritcle(Article article);
	public Iterable<Article> listArticles();

}
