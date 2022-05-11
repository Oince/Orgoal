# Orgoal 

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
- /
  - /signup
  - /signin
  - /search
  - /mypage/info
  - /mypage/activity
  - /newactivity
  - /activity/{id}
    - /activity/{id}/apply
    - /activity/{id}/list
    - /activity/{id}/submit
  - /notification/info

### POST /signup
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

### POST /signin
로그인 API
- req
  - email 이메일 id
  - password 비밀번호
- res
  - 성공시 200
  - 실패시 401

### GET /search?tag=""&query=""
액티비티 검색 API  
쿼리 스트링 tag, query 사용  
- req : x
- res
  - aid     액티비티 id
  - title   액티비티 제목
  - state   액티비티 상태
  - date    액티티비 마지막 수정 날짜
  - writer  작성자 id


### GET /mypage/info
mypage 정보 API
- req
  - 로그인 토큰
- res
  - nickname 유저 닉네임

### GET /mypage/activity
mypage 액티비티 정보 API
- req
  - 로그인 토큰
- res
  - aid     액티비티 id
  - title   액티비티 제목
  - state   액티비티 상태

### POST /newactivity
액티비티 등록 API
- req
  - 로그인 토큰
  - title   액티비티 제목
  - content 액티비티 본문
- res
  - aid     액티비티 id
  - 성공시 201 코드
  - 실패시 미정

### GET /activity/{id}
액티비티 관련 정보를 가져오는 API
- req
  - 로그인 토큰
- res
  - title 액티비티 제목
  - content 액티비티 본문
  - email   작성자 이메일 id
  - nickname 작성자 닉네임
  - lastModification 작성 날짜


이 이후의 API는 관련 DB 설계 전이기 때문에 변경 가능성이 있음

### POST /activity/{id}/apply
액티비티 신청 API  
- req
  - 로그인 토큰
  - answer 액티비티 질문 답변 내용
- res
  - 성공시 201
  - 실패시 미정

### GET /activity/{id}/list
해당 액티비티의 신청자를 가져오는 API  
로그인 토큰을 통해 액티비티 게시자만 접근 가능
- req   
  - 로그인 토큰
- res
  - email 신청자 이메일 id
  - nickname 신청자 닉네임
  - answer 신청자의 질문 내용

### POST /activity/{id}/submit?email=""
해당 액티비티 신청자의 신청을 수락하는 API  
email 쿼리 스트링 사용
- req
  - 로그인 토큰
- res
  - 성공시 201
  - 실패시 미정

### GET /notification/info
알림센터 정보를 가져오는 API
- req
  - 로그인 토큰
- res
  - state 신청 수락 or 요청
  - aid   해당 액티비티 id
