package com.example.bulletinboardproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Spirng Boot 2.7에 대응하여 Security 설정.

@Configuration
public class SecurityConfig {
    // client가 request 요청을 보내면, Spring Container 이전에 Servlet Container에서 먼저 Filter에 의해 여러 가지 요청이 처리됩니다.
    // SpringContainer와 Was 서버 간에 request 요청이 연결되어야 하는데, 이를 수행하는 Filter가 DelegatingFilterProxy입니다.

    // DelegatingFilterProxy는 SpringContainer에 존재하는 FilterChainProxy에게 해당 요청을 위임함으로써,
    // request를 Spring Container에서 처리할 수 있습니다.
    // 위임 전략을 사용하는 이유는, 스프링에서 제공하는 다양한 기술들을 사용할 수 있기에 다양한 장점이 있다고 합니다.

    // FilterChainrProxy로 위임 받은 필터는 SecurityFilterChain이라는 구현체를 가지고 있고 그 안에 다양한 SecurityFilter가 존재합니다.
    // 사진에 보이는 작은 글씨의 0, n은 SecurityFilter가 다수의 필터를 가질 수 있음을 의미하고,
    // 배열에 관리되기 때문에 각 인덱스에는 필터의 중요도(처리 우선순위)라고 할 수 있습니다.

    // SecurityFilter_0 ~ SecurityFilter_n 까지 반복문이 돌면서, 해당 request에 매핑되는 antMatcher를 찾는다면,
    // 필터는 해당 filter의 메소드에 작성된 체인들을 설정 요구사항을 확인하며 처리합니다.
    // 최종적으로 filter를 통과하면, DispatcherSerlvet으로 요청이 위임하여 성공 핸들러(사용자 정의 핸들러 혹은 controller)가 실행됩니다.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin().and()
                .build();
    }
}