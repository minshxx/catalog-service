package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJdbcTest   // <-- 스프링 데이터 JDBC컴포넌트를 집중적으로 테스트하는 클래스(트랜잭션 완료 후 자동 롤백)
@Import(DataConfig.class)   // <-- 데이터 설정을 임포트
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // <-- 테스트 컨테이너를 이용해야하기 때문에 내장 테스트 데이터베이스 사용을 비활성화한다.
@ActiveProfiles("integration")  // <-- application-integration.yml에서 설정을 로드하기 위해 integration 프로파일을 활성화한다.
public class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;    // <-- 데이터베이스와 상호작용하기 위한 하위 수준의 객체
}
