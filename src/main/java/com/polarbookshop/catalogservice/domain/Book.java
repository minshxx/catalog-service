package com.polarbookshop.catalogservice.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.Version;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.Instant;

public record Book(
        @Id
        Long id,

        @NotBlank(message = "The Book ISBN must be defined.")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "The ISBN format must be valid."
        )
        String isbn,

        @NotBlank(message = "The book title must be defined")
        String title,

        @NotBlank(message = "The book author must be defined.")
        String author,

        @NotNull(message = "The book price must be defined.")
        @Positive(message = "The book price must be greater than zero")
        Double price,

        // 엔티티 생성 Audit
        @CreatedBy
        Instant createdDate,

        // 엔티티 수정 Audit
        @LastModifiedBy
        Instant lastModifiedDate,

        /** Optimistic Locking을 위해 사용되는 엔티티 버전 번호
         *
         * 동일 엔티티가 여러 스레드에 의해 상태변화가 발생하려 할 버전을 체크해 예외를 발생 시킴
         * (id필드가 null이고 버전이 0이면 새로은 엔티티로 인식)
         */
        @Version
        int version
) {
        /**
         * 필요한 인자 값만 전달받아 객체를 생성하는 정적 팩토리 메서드
         *
         * id가 null 이고 버전이 0이면 새로운 엔티티로 인식한다.
         * audit항목은 spring data가 자동으로 지정
         * */
        public static Book of(String isbn, String title, String author, Double price) {
                return new Book(null, isbn, title, author, price, null, null, 0);
        }
}
