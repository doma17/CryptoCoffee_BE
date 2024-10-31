
# CryptoCoffee Backend

CryptoCoffee 프로젝트는 [Planz-coffee](https://planz-coffee.com) 시스템을 기반으로 블록체인 기반의 DID(Decentralized Identifier) 인증 시스템을 테스트하기 위해 개발 되었습니다. 탈중앙화된 신원 확인 절차를 통해 사용자 정보를 보호하면서도 신뢰성 있는 인증을 제공합니다. DID를 활용한 인증 시스템의 작동 원리와 그 유용성을 실험하고 평가하기 위해 본 프로젝트가 진행되었습니다.


## 목차
- [주요기능](#주요-기능)
- [개발의도](#개발-의도)
- [모듈트리구조](#모듈-트리-구조)
- [배포](#배포)

## 주요 기능
> - DID 인증 : 블록체인 기술을 사용한 탈중앙화 인증 방식을 통해 사용자의 신원을 확인하고 인증합니다.
> - 거래 처리 : DID 인증된 사용자 간의 거래를 처리하고 기록합니다.
> - 데이터 관리 : 사용자 관련 데이터 및 사용자 정보(탈중앙화 방식으로 처리)를 관리합니다.

## 개발 의도

<p align="center"><img src="https://github.com/user-attachments/assets/d3a8ab5d-2a84-4e6c-9453-4d380cbd6ff6" width="70%" height="70%"></p>
<p align="center"><img src="https://github.com/user-attachments/assets/b4775ec6-0926-4c6e-88d2-0f278a77e375" width="70%" height="70%"></p>
<p align="center"><img src="https://github.com/user-attachments/assets/fe7d7c7f-cb15-499c-b504-aad3ecd0b5b2" width="70%" height="70%"></p>

![image](https://github.com/user-attachments/assets/152c468e-16d8-4168-a1db-f54cc4c0a32a)

## 모듈 트리 구조

```plaintext
.
└── cryptocoffee
    ├── auth                    # 인증 모듈
    │   ├── email               # 이메일 인증 관련 기능
    │   │   ├── controller
    │   │   ├── dto
    │   │   ├── entity
    │   │   ├── repository
    │   │   └── service
    │   └── user                # 유저 JWT 인증/인가 모듈
    │       ├── controller
    │       ├── dto
    │       ├── entity
    │       ├── jwt
    │       ├── repository
    │       ├── service
    │       └── util
    ├── domain                  # 도메인 모듈
    │   ├── company
    │   ├── item
    │   ├── member
    │   └── order
    └── global                  # 전역 설정 모듈
        ├── config
        ├── entity
        ├── exception
        └── test
```

## 배포
이 프로젝트는 GitHub Actions를 사용하여 CI/CD가 자동화되어 있었으며, `Dockerfile`과 `docker-compose.yml` 파일이 포함되어 있어 컨테이너화된 배포가 가능합니다.
