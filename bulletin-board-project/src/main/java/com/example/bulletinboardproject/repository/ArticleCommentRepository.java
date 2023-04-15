package com.example.bulletinboardproject.repository;

import com.example.bulletinboardproject.domain.ArticleComment;
import com.example.bulletinboardproject.domain.QArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;

@RepositoryRestResource
// - @RepositoryRestResource : Spring Boot Data REST에서 지원하는 어노테이션.
//      별도의 컨트롤러와 서비스 영역 없이 미리 내부적으로 정의되어 있는 로직을 따라 처리됨.
//      그 로직은 해당 도메인의 정보를 매핑하여 REST API를 제공하는 역할을 함.

public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment>{

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content, root.createdAt, root.createdBy);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

}