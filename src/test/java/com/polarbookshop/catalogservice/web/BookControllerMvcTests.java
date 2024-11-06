package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 스프링 MVC 컴포넌트에 중점을 두고, 명시적으로는 BookController 클래스를 타겟으로 하는 테스트 클래스임을 나타낸다.
@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

    // 모의 환경에서 웹 계층을 테스트하기 위한 유틸리티 클래스
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "73737313940";

        // 모의 빈이 어떻게 작동할 것인지 규정
        given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);

        mockMvc
                // MockMvc는 Http Get 요청을 수행하고 결과를 확인하기 위해 사용한다.
                .perform(get("/book/" + isbn))
                // 응답이 404상태로 예상
                .andExpect(status().isNotFound());
    }

}
