package com.tieto.javabootcamp.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.model.text.Comment;
import com.tieto.javabootcamp.repository.ArticleRepository;
import com.tieto.javabootcamp.repository.CommentRepository;
import com.tieto.javabootcamp.service.ArticleService;
import com.tieto.javabootcamp.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public Comment saveAritcle(Comment article) {
		if (article.getId() == null) {
			article.setCreatedAt(LocalDateTime.now());
		}
		return commentRepository.save(article);
	}

	@Override
	public Iterable<Comment> listArticles() {
		return commentRepository.findAll();
	}

}
