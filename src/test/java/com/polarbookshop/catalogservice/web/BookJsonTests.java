package com.polarbookshop.catalogservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

// Json 직렬화에 중점을 둔 테스트 클래스임을 나타낸다.
@JsonTest
class BookJsonTests {

    // Json직렬화 및 역 직렬화를 확인하기 위한 유틸리티 클래스
    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = Book.of("1234567890", "Title", "Author", 9.90);
        var jsonContent = json.write(book);

        // JsonPath형식을 사용해 JSON객체를 탐색하고 자바의 Json변환을 확인한다.
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws Exception {
        Map<String, Object> map = new HashMap<>() {};

        map.put("isbn", "1234567890");
        map.put("title", "Title");
        map.put("author", "Author");
        map.put("price", 9.90);

        // 자바 텍스트 블록 기능을 사용해 Json객체를 정의한다.  안되넹....
//        var content = """
//                {
//                    "isbn": "1234567890",
//                    "title": "Title",
//                    "author: "Author",
//                    "price": 9.90
//                }
//                """;

        var content = new ObjectMapper().writeValueAsString(map);

        // Json에서 자바 객체로의 변환을 확인한다.
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(Book.of("1234567890", "Title", "Author", 9.90));

    }
}
