package com.example.bulletinboardproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.Optional;
@EnableJpaAuditing
// @EnableJpaAuditing: Configuration 어노테이션을 통해 JPA 에서 auditing을 가능하게 하는 어노테이션.
@Configuration
public class JpaConfig {
    // [ 생성일자와 수정일자 "자동" 등록 ]
    // => 객체를 생성하거나 수정할 때, 생성자와 Setter에 LocalDateTime.now() 등
    //    시간을 나타내는 객체를 넣어 생성일자와 수정일자를 관리할 수 있습니다.
    //    하지만 여러 엔터티에서 이러한 코딩을 매번 하는 것은 단순하고 번거로운 작업입니다.
    // 결론 : 인스턴스를 생성하거나 수정하는 변화가 있을 시에
    //       이를 감지하여 "자동"으로 일시를 저장하도록 합니다.

    @Bean
    // private String modifiedBy;
    // 수정자가 리턴할 때마다 수정자를 자동으로 작성. 현재 임의로 작성함. (test)
    // <String> => 사람 이름 자료형.
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("test"); // TODO: 스프링 시큐리티로 인증기능을 넣을 예정.
    }
}
