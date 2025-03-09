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
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

### BackEnd-Framework
![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white)

### DB
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

### ORM
![JPA](https://img.shields.io/badge/JPA-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white)

### TEST
![Junit5](https://img.shields.io/badge/junit-%23E33332?logo=junit5&logoColor=white)

---

## 아키텍처

<img src="https://github.com/Quizly-Project/.github/raw/main/profile/img/%EC%95%84%ED%82%A4%ED%85%8D%EC%B3%90.png" />

Quizly는 프론트엔드, 백엔드, 실시간 통신 서버, 그리고 화상 통화 서버로 구성된 복합적인 아키텍처를 가지고 있습니다. 각 컴포넌트는 다음과 같은 역할을 합니다:

- **[프론트엔드](https://github.com/Quizly-Project/quizly-frontend)**: React와 Three.js를 사용하여 3D 퀴즈 환경과 사용자 인터페이스를 구현
- **[백엔드](https://github.com/Quizly-Project/quizly-Spring)**: Spring Boot를 사용하여 RESTful API 제공 및 데이터 관리
- **[실시간 통신 서버](https://github.com/Quizly-Project/quizly-Nest)**: Nest.js와 Socket.io를 사용하여 실시간 퀴즈 상호작용 구현
- **화상 통화 서버**: LiveKit을 사용하여 참가자 간 화상 통화 기능 제공

이러한 구조를 통해 Quizly는 몰입감 있는 3D 퀴즈 경험과 실시간 상호작용을 효과적으로 제공합니다.

## ERD 
<img width="1349" alt="image" src="https://github.com/user-attachments/assets/16d093e5-1cd6-4694-947a-dd0b20fbe995" />

## 구현한 API 목록 
### 퀴즈 그룹 API
<img width="1307" alt="image" src="https://github.com/user-attachments/assets/cdad3bd1-6cb1-43ac-9f70-41ae9b4c5304" />

### 퀴즈 결과 API
<img width="1311" alt="image" src="https://github.com/user-attachments/assets/25844a02-8334-49e9-a080-573f23f3b106" />


## 서버 주요 기능 
- 유저 회원 가입 및 로그인 
- 퀴즈 묶음 생성하기
- 퀴즈 묶음 불러오기
- 퀴즈 결과 저장하기
- 퀴즈 결과 불러오기 


프로젝트의 전체 구조는 [STRUCTURE.md](./STRUCTURE.md)에서 확인할 수 있습니다.
