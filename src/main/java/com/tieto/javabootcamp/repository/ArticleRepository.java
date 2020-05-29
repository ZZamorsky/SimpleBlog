package com.tieto.javabootcamp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.tieto.javabootcamp.model.text.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

	Page<Article> findAll(Pageable firstPageWithThreeElements);

	Optional<Article> findById(Long id);
}
