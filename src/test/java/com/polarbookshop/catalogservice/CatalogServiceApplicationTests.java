package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {}

    @Test
    void whenPostRequestThenBookCreated() {
        var expectedBook = Book.builder()
                .isbn("1231231231")
                .title("Title")
                .author("Author")
                .price(9.90)
                .build();

        webTestClient
                .post()
                .uri("/books")
                .bodyValue(expectedBook)
                .exchange() // <-- 요청을 전송
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.isbn())
                            .isEqualTo(expectedBook.isbn());
                });
    }
}