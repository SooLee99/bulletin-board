package com.example.bulletinboardproject.controller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Data REST - API 테스트")
// @DisplayName() : 테스트 클래스 혹은 테스트 메서드의 이름을 지정.
@Transactional
// @Transactional : 트랜잭션 AOP를 위해 Spring에서 지원해주는 어노테이션이다.

// - AOP(Aspect-Oriented Programming)
//      애플리케이션 기능은 크게 핵심 기능과 부가 기능으로 나눌 수 있다.
//      하나의 부가 기능(핵심 기능을 보조하는 기능)은 여러 곳에서 동일하게 사용되는 경우가 많다.
//      sql 호출 실패하면 rollback, 성공하면 commit.. 반복적이고 지루한 작업들!
//      똑같은 로직을 여러 곳에 적용하기란 너무 번거롭다.
//      부가 기능에 수정 사항 발생하면 이 로직들을 죄다 수정해야할 수도 있다.
//      그렇다면 어떻게 부가 기능을 한번에 관리할 수 있을까?
//      부가 기능을 분리하고 이 기능을 어디에 적용할지 선택하는 기능을 합쳐 하나의 모듈로 만든 것이 Aspect다.

@AutoConfigureMockMvc
// @AutoConfigureMockMvc() : Mock 테스트 시 필요한 의존성을 제공.
@SpringBootTest
// @SpringBootTest : 통합 테스트를 제공하는 기본적인 스프링 부트 테스트 어노테이션.
public class DataRestTest {

    private final MockMvc mvc;
    // MockMvc : 웹 어플리케이션을 애플리케이션 서버에 배포하지 않고,
    //           테스트용 MVC환경을 만들어 요청 및 전송, 응답기능을 제공해주는 유틸리티 클래스다.
    // => 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())  // 상태 확인.
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));  // hal+json 타입으로 get 받음.
    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticle_thenReturnsArticleJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 게시글 -> 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleCommentsFromArticle_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticleComment_thenReturnsArticleCommentJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
}