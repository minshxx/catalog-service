package com.polarbookshop.catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@Configuration
@EnableJdbcAuditing // <-- 지속성 엔티티에 대한 감사(Auditing을 활성화 - auditing메타 데이터를 받아 엔티티에 추가)
public class DataConfig {}
