# blog

## 이 프로젝트

**Spring Boot 기반의 평범한 CRUD 학습용 프로젝트**입니다. 게시글·카테고리 같은 도메인을 **생성·조회·수정·삭제**하는 흐름을 익히고, Thymeleaf·REST·DB 연동 등을 실습하는 것이 목적에 가깝습니다.

**만든 이유**는 공부한 내용을 **코드와 설정으로 남기고**, 필요하면 **블로그·노트 등 다른 곳**에서 글로 풀어 정리하기 위해서입니다.

## 기술 스택

| 구분 | 내용 |
|------|------|
| 언어 | Java 21 |
| 프레임워크 | Spring Boot 4.0.5 |
| 빌드 | Gradle (Wrapper 8.12.1) |
| 화면 | Spring Thymeleaf |
| 데이터 | Spring JDBC, MariaDB (Docker) |
| API 문서 | springdoc-openapi 3.0.3 (Swagger UI) |
| 기타 | Lombok, Spring Boot DevTools |

## 사전 요구 사항

- JDK 21 이상

## 실행 방법

1) MariaDB 실행

```bash
docker compose up -d
```

2) 애플리케이션 실행

```bash
./gradlew bootRun
```

기본 포트는 `8080`입니다. (`server.port`를 `src/main/resources/application.yaml`에 두면 변경할 수 있습니다.)

종료할 때는 아래 명령으로 DB 컨테이너까지 내릴 수 있습니다.

```bash
docker compose down
```

## 문서·도구 URL

| 설명 | URL |
|------|-----|
| Swagger UI | http://localhost:8080/swagger-ui/index.html |
| OpenAPI JSON | http://localhost:8080/v3/api-docs |

## DB 연결 정보 (MariaDB)

MariaDB 기본 접속 정보는 `src/main/resources/application.yaml`과 `docker-compose.yml`에 맞춰 아래로 설정되어 있습니다.

- host: `localhost`
- port: `3306`
- database: `blog`
- username: `blog`
- password: `blog1234`

## 빌드·테스트

```bash
./gradlew build
```

## 프로젝트 구조 (요약)

```
src/main/java/com/demo/blog/     # 진입점 및 이후 CRUD·도메인 코드
src/main/resources/
  application.yaml                # 앱·springdoc 등 설정
```

## 개발 시 참고

- REST 컨트롤러를 추가하면 Swagger UI에 엔드포인트가 자동으로 노출됩니다.
- Spring Security를 도입하면 `/swagger-ui/**`, `/v3/api-docs/**` 경로를 허용 목록에 넣는 설정이 필요할 수 있습니다.
# study-log
