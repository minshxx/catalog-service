package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

// 완전한 스프링 웹 애플리케이션 컨텍스트와 임의의 포트를 듣는 서블릿 컨테이너를 로드한다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

    // 테스트를 위해 REST엔드포인트를 호출할 유틸리티
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenPostRequestThenBookCreated() {
        var expectedBook = Book.of("1231231231", "Title", "Author", 9.90);

        webTestClient
                .post()                                         // <-- HTTP POST 요청
                .uri("/books")                              // <-- 요청 엔드포인트
                .bodyValue(expectedBook)                        // <-- 요청 Body추가
                .exchange()                                     // <-- 요청을 전송
                .expectStatus().isCreated()                     // <-- 응답 상태코드가 201생성을 갖는지 확인
                .expectBody(Book.class).value(actualBook -> {   // <-- 응답 Body
                    assertThat(actualBook).isNotNull();         // <-- Null이 아닌지 체크
                    assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());   // <-- 생성된 객체가 예상과 동일한지 확인
                });
    }
}