package com.tieto.javabootcamp.service;

import com.tieto.javabootcamp.model.text.Comment;
import com.tieto.javabootcamp.model.text.PrivateMessage;

public interface PrivateMessageService {
	
	public PrivateMessage saveAritcle(PrivateMessage article);
	public Iterable<PrivateMessage> listArticles();

}
