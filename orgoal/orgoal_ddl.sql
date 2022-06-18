Create table PasswordQuestion (
    question    varchar(20) primary key
);

Insert into PasswordQuestion values('ELEMENTARYSCHOOL');
Insert into PasswordQuestion values('PETNAME');
Insert into PasswordQuestion values('FAVORITECOLOR');

Create sequence member_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9999;

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


Create sequence activity_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9999;

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

Create table participate (
    aid                 numeric(4,0),
    mid                 numeric(4,0),
    application_date    date not null,
    state               char(1) not null,
    changed             char(1) not null,
    answer              varchar(500),
    constraint pk primary key(aid, mid),
    constraint fk_part_aid foreign key(aid) references activity,
    constraint fk_part_mid foreign key(mid) references member,
    constraint check_part_state check (state in ('W','A')),
    constraint check_changed check (changed in ('T', 'F'))
);

