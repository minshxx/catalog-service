package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends CrudRepository<Book,Long> {
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    @Modifying      // <-- 데이터베이스 상태를 수정할 연산임을 나타낸다.
    @Transactional  // <-- 해당 메서드가 트랜잭션으로 실행됨을 나타낸다.
                    //     CrudRepository의 메소드는 Transaction내에서 수행되지만 사용자 지정쿼리를 사용할 경우에는 @Transaction 어노테이션을 붙여야 Transaction내에서 실행
    @Query("DELETE FROM BOOK WHERE ISBN = :ISBN")
    void deleteByIsbn(String isbn);
}
