# Joil Backend

Spring Boot와 Kotlin으로 개발된 백엔드 애플리케이션입니다. MongoDB를 데이터베이스로 사용하며, RESTful API를 제공합니다.

## 기술 스택

- **Language**: Kotlin
- **Framework**: Spring Boot 3.5.6
- **Database**: MongoDB 7.0
- **Java Version**: 21
- **Build Tool**: Gradle (Kotlin DSL)

### 주요 의존성

- Spring Boot Starter Web
- Spring Boot Starter Data MongoDB (Reactive)
- Spring Session Data MongoDB
- Mongock (Database Migration)
- Arrow-kt (Functional Programming)
- Jackson Kotlin Module
- Kotlinx Coroutines

## 프로젝트 구조

```
src/
├── main/
│   ├── kotlin/
│   │   └── com/spring/joilbackend/
│   │       ├── JoilBackendApplication.kt    # 메인 애플리케이션 클래스
│   │       ├── common/                      # 공통 유틸리티 및 예외 처리
│   │       ├── config/                      # 설정 클래스들
│   │       ├── controller/                  # REST 컨트롤러
│   │       ├── entity/                      # MongoDB 엔티티
│   │       ├── migration/                   # 데이터베이스 마이그레이션
│   │       ├── model/                       # 데이터 모델/DTO
│   │       ├── repository/                  # 데이터 접근 계층
│   │       └── service/                     # 비즈니스 로직
│   └── resources/
│       ├── application.properties           # 애플리케이션 설정
│       ├── static/                          # 정적 리소스
│       └── templates/                       # 템플릿 파일
├── test/                                    # 테스트 코드
└── docker/                                  # Docker 관련 파일
    ├── docker-compose.yml                   # MongoDB 컨테이너 설정
    └── mongo-init/
        └── 01-init-joil-db.js              # MongoDB 초기화 스크립트
```

## 로컬 개발 환경 설정

### 사전 요구사항

- Java 21
- Docker
- Git

### 1. 프로젝트 클론

```bash
git clone <repository-url>
cd joil-backend
```

### 2. MongoDB 실행

Docker Compose를 사용하여 MongoDB와 Mongo Express를 실행합니다:

```bash
cd docker
docker-compose up -d
```

실행되는 서비스:
- **MongoDB**: `localhost:27017`
  - 사용자: `joiluser`
  - 비밀번호: `joilpassword`
  - 데이터베이스: `joil`
- **Mongo Express** (웹 UI): `localhost:8081`

### 3. 애플리케이션 실행

#### Gradle을 사용한 실행

```bash
# 프로젝트 루트 디렉토리에서
./gradlew bootRun
```

#### IDE에서 실행

IntelliJ IDEA 또는 다른 IDE에서 `JoilBackendApplication.kt` 파일을 실행합니다.

### 4. 애플리케이션 확인

애플리케이션이 성공적으로 실행되면 기본적으로 `http://localhost:8080`에서 접근할 수 있습니다.

## 개발 도구

### 빌드

```bash
# 프로젝트 빌드
./gradlew build

# 테스트 실행
./gradlew test

# 클린 빌드
./gradlew clean build
```

### 로그 확인

애플리케이션 로그는 `logs/joil-backend.log` 파일에 저장됩니다.

### 데이터베이스 관리

- **Mongo Express**: `http://localhost:8081`에서 MongoDB 데이터를 웹 브라우저로 확인할 수 있습니다.
- **MongoDB CLI**: `mongosh mongodb://joiluser:joilpassword@localhost:27017/joil`
