package com.tieto.javabootcamp.repository;

import org.springframework.data.repository.CrudRepository;

import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.model.text.PrivateMessage;

public interface PrivateMessageRepository extends CrudRepository<PrivateMessage, Long> {

}
