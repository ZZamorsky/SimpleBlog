package com.tieto.javabootcamp.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.tieto.javabootcamp.Component.AccessRights;
import com.tieto.javabootcamp.exception.BadRequestException;
import com.tieto.javabootcamp.exception.NotFoundException;
import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.repository.ArticleRepository;
import com.tieto.javabootcamp.repository.UserRepository;
import com.tieto.javabootcamp.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Article saveArticle(Article article, User user) {
		if (article.getId() == null) {
			article.setCreatedAt(LocalDateTime.now());
		}
		if (user == null) {
			throw new BadRequestException("No author specified");
		}
		article.setAuthor(
			userRepository.findByName(user.getUsername())
				.orElseThrow(() -> new NotFoundException("Author with supplied id not found"))
		);
		return articleRepository.save(article);
	}

	@Override
	public Iterable<Article> listArticles() {
		return articleRepository.findAll();
	}

	@Override
	public void removeArticle(Article article, User user) {
    	if (AccessRights.isAproved(article, user)) {
    		if (articleRepository.findById(article.getId()).isPresent()) {
    		articleRepository.deleteById(article.getId());
    		} 
    		else {
    		throw new NotFoundException("Article with supplied id does not exist");
    		}
    			
    	}
    	else throw new BadRequestException("No permission for this operation");
    }
	

	@Override
	public Article updateArticle(Article article, User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
