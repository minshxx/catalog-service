-- 정규버전 마이그레이션파일은 아래의 명명 패턴을 따라야함
-- 접두사: 버전 마이그레이션은 V를 사용
-- 버전: 점이나 밑줄을 통해 여러 부분으로 구분되는 버전 번호(ex. 2.0.1)
-- 구분자: __
-- 설명: 밑줄로 구분되는 하나 이상의 단어
CREATE TABLE BOOK (
    ID                  BIGSERIAL PRIMARY KEY NOT NULL,
    AUTHOR              VARCHAR(255) NOT NULL,
    ISBN                VARCHAR(255) UNIQUE NOT NULL,
    PRICE               FLOAT8 NOT NULL,
    TITLE               VARCHAR(255) NOT NULL,
    CREATED_DATE        TIMESTAMP NOT NULL,
    LAST_MODIFIED_DATE  TIMESTAMP NOT NULL,
    VERSION             INTEGER NOT NULL
);