# Orgoal 
**수정사항**

- GET /api/activity/{id}/member 추가
- GET /api/activity/{id} 에서 mid 대신에 이메일로 바꿈


프론트 작업은 orgoal/src/front에서 해주시고  
빌드 결과물은 orgoal/src/main/recources/static 에 출력해주세요  

프론트 페이지 빌드 오류 시 해당 디렉토리에서  
```
npm install
```
실행해서 필요한 패키지 일괄 다운 ㄱ  

## DB 저장 정보

### member
- mid       멤버 primary key
- email     유저 이메일, unique 제약
- password  비밀번호, sha-256 으로 암호화
- nickname  닉네임
- question  비밀번호 찾기 질문, 밑의 3가지중 하나
    - PETNAME
    - ELEMENTARYSCHOOL
    - FAVORITECOLOR
- answer    질문에 대한 답
```sql
Create table Member(
    mid          numeric(4,0) primary key,
    email       varchar(50) not null,
    password    char(64) not null,
    nickname    varchar(50) not null,
    question    varchar(20) not null,
    answer      varchar(100) not null,
    constraint fk_question foreign key(question) 
    references passwordquestion(question),
    constraint unique_email unique(email)
);
```

### activity
- aid       액티비티 id
- title     액티비티 제목
- content   액티비티 본문
- state     액티비티 상태, 밑의 3가지중 하나
  - R   모집중
  - P   진행중
  - E   종료
- mid 멤버 테이블 외래키
- last_modification 마지막 수정 시간, 생성시 생성 시간
```sql
Create table activity (
    aid                 numeric(4,0) primary key,
    title               varchar(100) not null,
    content             varchar(500) not null,
    state               char(1) not null,
    mid                 numeric(4,0) not null,
    last_modification   date not null,
    constraint check_state check (state in ('R', 'P', 'E')),
    constraint fk_mid foreign key(mid) references member
);
```

## API

### 개요
- /api
  - **/signup 완료**
  - **/signin 완료**
  - **/search 완료**
  - **/nickname 완료**
  - **/mypage/info 완료**
  - **/mypage/activity 완료**
  - **/activity 완료**
    - **/activity/{id} 완료**
    - **/activity/{id}/list 완료**
    - **/activity/{id}/accept? 완료**
    - **/activity/{id}/member 완료**

### POST /api/signup
회원가입 API
- req
  - email 이메일id
  - password 비밀번호
  - nickname 닉네임
  - question 비밀번호 찾기 질문 3개중 하나
  - answer 비밀번호 찾기 질문의 답
- res
  - 성공시 201
  - 실패시 409

### POST /api/signin
로그인 API
- req
  - email 이메일 id
  - password 비밀번호
- res
  - token 토큰
  - 성공시 200
  - 실패시 401
  

### GET /api/search?query=""
액티비티 검색 API  
쿼리 스트링 tag, query 사용  
query에 해당하는 결과가 없으면 전체 액티비티를 최신순으로 보내줌

- req : x
- res
  - aid     액티비티 id
  - title   액티비티 제목
  - content 액티비티 내용
  - state   액티비티 상태
  - mid 작성자id
  - lastModification    액티티비 마지막 수정 날짜
  - 성공시 200
  - 검색 결과가 없으면 전체 리스트 최신순으로 보냄 코드 202

### GET /api/nickname

이메일에 해당하는 유저의 닉네임을 가져오는 API  

- req 
  - token 로그인 토큰
- res
  - nickname 해당 유저의 닉네임


### GET /api/mypage/info
mypage 정보 API
- req
  - 로그인 토큰
- res
  - nickname 유저 닉네임
  - 성공시 200
  - 실패시 401 or 404

### GET /api/mypage/activity
mypage 액티비티 정보 API
- req
  - 로그인 토큰
- res
  - aid     액티비티 id
  - title   액티비티 제목
  - state   액티비티 상태

### POST /api/activity
액티비티 등록 API
- req
  - 로그인 토큰
  - title   액티비티 제목
  - content 액티비티 본문
- res
  - aid     액티비티 id
  - 성공시 201 코드
  - 실패시 401 또는 404

### GET /api/activity/{id}
액티비티 관련 정보를 가져오는 API
- req
  - 로그인 토큰
- res
  - aid 액티비티 아이디
  - title 액티비티 제목
  - content 액티비티 본문
  - state 액티비티 상태
  - email 작성자 이메일id
  - nickname 작성자 닉네임
  - lastModification 작성 날짜
  - 성공시 200
  - 실패시 404
  

### POST /api/activity/{id}
액티비티 신청 API  
- req
  - 로그인 토큰
  - answer 액티비티 질문 답변 내용
- res
  - 성공시 201
  - 실패시 404 or 409

### GET /api/activity/{id}/list
해당 액티비티의 신청자를 가져오는 API  
로그인 토큰을 통해 액티비티 게시자만 접근 가능

- req   
  - 로그인 토큰
- res
  - email 신청자 이메일 id
  - nickname 신청자 닉네임
  - answer 신청자의 질문 내용

### POST /api/activity/{id}/accept?email=""
해당 액티비티 신청자의 신청을 수락하는 API  
email 쿼리 스트링 사용

- req
  - 로그인 토큰
- res
  - 성공시 201
  - 실패시 400

### GET /api/activity/{id}/member

해당 액티비티 참가자를 가져오는 API

- req 
  - 로그인 토큰
- res
  - email 참가자 이메일
  - nickname 참가자 닉네임
