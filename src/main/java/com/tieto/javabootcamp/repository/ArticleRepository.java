package com.tieto.javabootcamp.repository;

import org.springframework.data.repository.CrudRepository;

import com.tieto.javabootcamp.model.text.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
