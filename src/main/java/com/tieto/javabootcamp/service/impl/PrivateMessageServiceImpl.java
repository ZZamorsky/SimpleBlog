package com.tieto.javabootcamp.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.model.text.PrivateMessage;
import com.tieto.javabootcamp.repository.ArticleRepository;
import com.tieto.javabootcamp.repository.PrivateMessageRepository;
import com.tieto.javabootcamp.service.ArticleService;
import com.tieto.javabootcamp.service.PrivateMessageService;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

	@Autowired
	private PrivateMessageRepository privateMessageRepository;
	
	@Override
	public PrivateMessage saveAritcle(PrivateMessage article) {
		if (article.getId() == null) {
			article.setCreatedAt(LocalDateTime.now());
		}
		return privateMessageRepository.save(article);
	}

	@Override
	public Iterable<PrivateMessage> listArticles() {
		return privateMessageRepository.findAll();
	}

}
