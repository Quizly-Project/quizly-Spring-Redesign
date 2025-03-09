# Quizly-Spring

<div align="center">
<!-- <img width="329" alt="Quizly Logo" src="https://github.com/Quizly-Project/.github/raw/main/profile/img/logo.png"> -->

![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=Quizly&fontSize=50&animation=twinkling&text-color=black)

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/Quizly-Project/quizly-Spring&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

</div>

# Quizly: 3D 상호작용 기반의 몰입형 퀴즈 풀이 서비스

<br> **크래프톤 정글 5기 '나만의 무기 만들기' 프로젝트** <br/>
**개발기간: 2024.06.21 ~ 2024.07.27**

## 프로젝트 소개 영상

- [Quizly 프로젝트 소개 영상](https://youtu.be/sUz-6YQ4_yQ)
- [발표 영상](https://youtu.be/vFkri4LvJX4)

## 프로젝트 설명 포스터!


<img width="5100" alt="quizly_poster" src="https://github.com/user-attachments/assets/9fa20449-2ace-482d-8a38-34db8d4dd197">


## 팀 소개

|                           김현수                           |                             신동우                             |                           유영우                            |                           조재룡                           |                           황연경                            |
| :--------------------------------------------------------: | :------------------------------------------------------------: | :---------------------------------------------------------: | :--------------------------------------------------------: | :---------------------------------------------------------: |
| <img width="160px" src="https://github.com/hyunS00.png" /> | <img width="160px" src="https://github.com/NoNoise2022.png" /> | <img width="160px" src="https://github.com/yoo20370.png" /> | <img width="160px" src="https://github.com/jjr7181.png" /> | <img width="160px" src="https://github.com/yunnn426.png" /> |
|           [@hyunS00](https://github.com/hyunS00)           |         [@NoNoise2022](https://github.com/NoNoise2022)         |          [@yoo20370](https://github.com/yoo20370)           |           [@jjr7181](https://github.com/jjr7181)           |          [@yunnn426](https://github.com/yunnn426)           |

## 프로젝트 소개

Quizly는 3D 환경에서 실시간 상호작용을 통해 즐겁고 효과적인 학습 경험을 제공하는 퀴즈 플랫폼입니다. 사용자들은 몰입감 있는 3D 공간에서 다양한 퀴즈에 참여하고, 실시간으로 다른 참가자들과 경쟁할 수 있습니다.

## 시작 가이드

### 설치

```bash
$ git clone https://github.com/Quizly-Project/quizly-Spring.git
$ cd quizly-Spring
$ ./gradlew clean build
$ java -jar build/libs/quizly-Spring-0.0.1-SNAPSHOT.jar
```

---

## Stacks 🐈

### Core
![JavaScript](https://img.shields.io/badge/JavaScript-F7B93E?style=for-the-badge&logo=JavaScript&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white)

### Real-time Communication
![Socket.io](https://img.shields.io/badge/Socket.io-010101?style=for-the-badge&logo=socket.io&logoColor=white)
![LiveKit](https://img.shields.io/badge/LiveKit-2A2A2A?style=for-the-badge&logo=webrtc&logoColor=white)

### HTTP
![Axios](https://img.shields.io/badge/Axios-5A29E4?style=for-the-badge&logo=axios&logoColor=white)

### Development Tools
![ESLint](https://img.shields.io/badge/ESLint-4B32C3?style=for-the-badge&logo=eslint&logoColor=white)
![Prettier](https://img.shields.io/badge/Prettier-F7B93E?style=for-the-badge&logo=prettier&logoColor=black)
![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white)

### Environment
![npm](https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white)

---

## 화면 구성 📺

|                        메인 페이지                        |                          퀴즈 로비                          |
| :-------------------------------------------------------: | :---------------------------------------------------------: |
| <img width="329" src="path_to_main_page_screenshot.png"/> | <img width="329" src="path_to_quiz_lobby_screenshot.png"/>  |
|                       3D 퀴즈 환경                        |                         결과 페이지                         |
|  <img width="329" src="path_to_3d_quiz_screenshot.png"/>  | <img width="329" src="path_to_result_page_screenshot.png"/> |

---

## 주요 기능 📦

### ⭐️ 3D 환경에서의 실시간 퀴즈 참여

- Three.js를 활용한 몰입감 있는 3D 퀴즈 환경 제공
- 실시간 멀티플레이어 상호작용

### ⭐️ 다양한 퀴즈 모드

- 객관식, OX, 주관식 등 다양한 퀴즈 형식 지원

### ⭐️ 실시간 순위 및 점수 시스템

- Socket.io를 활용한 실시간 점수 업데이트 및 순위 표시

---

## 아키텍처

<img src="https://github.com/Quizly-Project/.github/raw/main/profile/img/%EC%95%84%ED%82%A4%ED%85%8D%EC%B3%90.png" />

Quizly는 프론트엔드, 백엔드, 실시간 통신 서버, 그리고 화상 통화 서버로 구성된 복합적인 아키텍처를 가지고 있습니다. 각 컴포넌트는 다음과 같은 역할을 합니다:

- **[프론트엔드](https://github.com/Quizly-Project/quizly-frontend)**: React와 Three.js를 사용하여 3D 퀴즈 환경과 사용자 인터페이스를 구현
- **[백엔드](https://github.com/Quizly-Project/quizly-Spring)**: Spring Boot를 사용하여 RESTful API 제공 및 데이터 관리
- **[실시간 통신 서버](https://github.com/Quizly-Project/quizly-Nest)**: Nest.js와 Socket.io를 사용하여 실시간 퀴즈 상호작용 구현
- **화상 통화 서버**: LiveKit을 사용하여 참가자 간 화상 통화 기능 제공

이러한 구조를 통해 Quizly는 몰입감 있는 3D 퀴즈 경험과 실시간 상호작용을 효과적으로 제공합니다.

## 서버 주요 기능 
- 다중 퀴즈 룸 생성 및 관리
- 퀴즈 정답 여부 판정 및 결과 관리 
- 실시간 점수 및 순위 업데이트
- 실시간 채팅
- 관리자 대시보드
- 클라이언트간 충돌 처리
- 모든 클라이언트 좌표 종합 및 브로드캐스트
- 좌표 양자화 및 역양자화

### 디렉토리 구조

```
quizly-Spring
├─ .gitignore
├─ HELP.md
├─ README.md
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
└─ src
   ├─ main
   │  ├─ generated
   │  ├─ java
   │  │  └─ Team9789
   │  │     └─ quizly_Spring
   │  │        ├─ HomeController.java
   │  │        ├─ QuizlySpringApplication.java
   │  │        ├─ ValidationGroups.java
   │  │        ├─ ValidationSequence.java
   │  │        ├─ config
   │  │        │  ├─ CorsMvcConfig.java
   │  │        │  ├─ EnvConfig.java
   │  │        │  └─ SecurityConfig.java
   │  │        ├─ controller
   │  │        │  ├─ AdminController.java
   │  │        │  ├─ JoinController.java
   │  │        │  ├─ MainController.java
   │  │        │  ├─ QuizController.java
   │  │        │  ├─ QuizGroupController.java
   │  │        │  ├─ QuizResultController.java
   │  │        │  ├─ QuizRoomController.java
   │  │        │  ├─ ReissueController.java
   │  │        │  └─ StudentResultController.java
   │  │        ├─ dto
   │  │        │  ├─ AnswerDto.java
   │  │        │  ├─ CustomUserDetails.java
   │  │        │  ├─ JoinDTO.java
   │  │        │  ├─ LoginDTO.java
   │  │        │  ├─ OptionDto.java
   │  │        │  ├─ QuizDto.java
   │  │        │  ├─ QuizGroupDto.java
   │  │        │  ├─ QuizPostDto.java
   │  │        │  ├─ RoomAnswersDto.java
   │  │        │  └─ StudentResultDto.java
   │  │        ├─ entity
   │  │        │  ├─ Quiz.java
   │  │        │  ├─ QuizGroup.java
   │  │        │  ├─ QuizOption.java
   │  │        │  ├─ QuizResult.java
   │  │        │  ├─ QuizRoom.java
   │  │        │  ├─ RefreshEntity.java
   │  │        │  ├─ RoomAnswers.java
   │  │        │  ├─ StudentResult.java
   │  │        │  └─ UserEntity.java
   │  │        ├─ jwt
   │  │        │  ├─ CustomLogoutFilter.java
   │  │        │  ├─ JWTFilter.java
   │  │        │  ├─ JWTUtil.java
   │  │        │  └─ LoginFilter.java
   │  │        ├─ repository
   │  │        │  ├─ QuizGroupRepository.java
   │  │        │  ├─ QuizOptionRepository.java
   │  │        │  ├─ QuizRepository.java
   │  │        │  ├─ QuizResultRepository.java
   │  │        │  ├─ QuizRoomRepository.java
   │  │        │  ├─ RefreshRepository.java
   │  │        │  ├─ StudentResultRepository.java
   │  │        │  └─ UserRepository.java
   │  │        └─ service
   │  │           ├─ CustomUserDetailsService.java
   │  │           ├─ JoinService.java
   │  │           ├─ QuizGroupService.java
   │  │           ├─ QuizResultService.java
   │  │           ├─ QuizRoomService.java
   │  │           ├─ QuizService.java
   │  │           └─ StudentResultService.java
   │  └─ resources
   │     ├─ application-local.yaml
   │     ├─ application.yaml
   │     ├─ properties
   │     │  └─ env.properties
   │     └─ templates
   │        ├─ index.html
   │        └─ login.html
   └─ test
      └─ java
         └─ Team9789
            └─ quizly_Spring
               ├─ QuizlySpringApplicationTests.java
               ├─ controller
               │  ├─ QuizControllerIntegrationTest.java
               │  └─ QuizResultControllerTest.java
               └─ service
                  └─ QuizServiceTest.java

```

프로젝트의 전체 구조는 [STRUCTURE.md](./STRUCTURE.md)에서 확인할 수 있습니다.
