package com.example.bulletinboardproject.repository;

import com.example.bulletinboardproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}