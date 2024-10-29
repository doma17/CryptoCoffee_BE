
# CryptoCoffee Backend

CryptoCoffee 프로젝트는 블록체인 기반의 DID(Decentralized Identifier) 인증 시스템을 테스트하기 위해 개발된 서비스입니다. 이 시스템은 탈중앙화된 신원 확인 절차를 통해 사용자 정보를 보호하면서도 신뢰성 있는 인증을 제공합니다. DID를 활용한 인증 시스템의 작동 원리와 그 유용성을 실험하고 평가하기 위해 본 프로젝트가 진행되었습니다.

> ## 주요 기능
> - DID 인증 : 블록체인 기술을 사용한 탈중앙화 인증 방식을 통해 사용자의 신원을 확인하고 인증합니다.
> - 거래 처리 : DID 인증된 사용자 간의 거래를 처리하고 기록합니다.
> - 데이터 관리 : 사용자 관련 데이터 및 사용자 정보(탈중앙화 방식으로 처리)를 관리합니다.

## 목차
- [설치](#설치)
- [사용 방법](#사용-방법)
- [구성](#구성)
- [API 문서](#api-문서)
- [배포](#배포)

## 설치

### 요구사항 
- Java 11 이상
- Docker (컨테이너화된 배포를 위해 필요)
- Gradle (빌드 관리용)

### 설치 단계
1. 레포지토리 클론:
   ```bash
   git clone https://github.com/doma17/CryptoCoffee_BE.git
   cd CryptoCoffee_BE
   ```

2. Gradle로 프로젝트 빌드:
   ```bash
   ./gradlew build
   ```

3. 로컬에서 애플리케이션 실행:
   ```bash
   ./gradlew bootRun
   ```

4. Docker로 애플리케이션 실행:
   ```bash
   docker-compose up --build
   ```

## 사용 방법
서버가 시작되면 애플리케이션은 Port 8080 에서 접근할 수 있습니다.

## 구성
데이터베이스 연결, 환경 변수, 애플리케이션 속성 등의 구성 정보는 `src/main/resources` 디렉토리에 있는 `application.yml` 또는 `application.properties` 파일에서 관리됩니다.

## API 문서
API 엔드포인트는 OpenAPI (Swagger)를 통해 문서화되어 있습니다. 애플리케이션 실행 후, 다음 링크에서 API 문서에 접근할 수 있습니다:
```
http://localhost:8080/swagger-ui.html
```

## 배포
이 프로젝트는 GitHub Actions를 사용하여 CI/CD가 자동화되어 있었으며, `Dockerfile`과 `docker-compose.yml` 파일이 포함되어 있어 컨테이너화된 배포가 가능합니다.

### 수동 배포 단계
1. Docker 이미지 빌드:
   ```bash
   docker build -t cryptoffee-backend .
   ```
2. 컨테이너 실행:
   ```bash
   docker run -p 8080:8080 cryptoffee-backend
   ```

