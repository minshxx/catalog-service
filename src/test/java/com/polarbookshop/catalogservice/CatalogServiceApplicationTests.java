package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

// 완전한 스프링 웹 애플리케이션 컨텍스트와 임의의 포트를 듣는 서블릿 컨테이너를 로드한다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")  // <-- application-integration.yml설정 파일을 로드하기 위해 integration프로파일을 활성화
class CatalogServiceApplicationTests {

    // 테스트를 위해 REST엔드포인트를 호출할 유틸리티
    @Autowired
    private WebTestClient webTestClient;
}