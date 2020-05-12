package com.tieto.javabootcamp.service;

import com.tieto.javabootcamp.model.text.Comment;

public interface CommentService {
	
	public Comment saveArticle(Comment article);
	public Iterable<Comment> listArticles();

}
