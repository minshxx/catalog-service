package com.polarbookshop.catalogservice.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    @Modifying
    @Transactional  // <-- 해당 메서드가 트랜잭션으로 실행됨을 나타낸다.
    @Query("DELETE FROM BOOK WHERE ISBN = :ISBN")
    void deleteByIsbn(String isbn);
}
