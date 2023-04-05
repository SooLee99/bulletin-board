package com.example.bulletinboardproject.repository;

import com.example.bulletinboardproject.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}