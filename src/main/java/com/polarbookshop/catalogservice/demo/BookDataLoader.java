package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")    // <-- 이 클래스는 testdata프로파일에 할당한다. testdata프로파일이 활성화될 때만 로드된다.
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /*
        ApplicationReadyEvent가 발생하면 테스트 데이터 생성이 시작된다.
        이 이벤트는 애플리케이션 시작 단계가 완료되면 발생한다.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();     // <-- 빈 데이터베이스로 시작하기 위해 기존 데이터는 모두 삭제
        var book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90, null);
        var book2 = Book.of( "1234567892", "Polar Journey", "Iorek Polarson",  12.90, "Polarsophia");

        bookRepository.saveAll(List.of(book1, book2));
    }
}
