CREATE TABLE Member (
    idx number(7) primary key,
    userid varchar2(20) unique not null,
    userpw varchar2(20)   not null,
    username varchar2(10)   not null,
    ssn varchar2(6)   not null, -- 주민등록번호 앞자리
    hp varchar2(13)   not null,
    email varchar2(50)   not null,
    nickname varchar2(20)   not null,
    valPoint number(10) DEFAULT 0, -- 사용가능한 포인트
    reg_date date not null
);

CREATE TABLE Theater (
    tcode number(7) primary key,
    tname varchar2(10) not null, -- 극장이름 강남CGV
    location varchar2(200) not null, -- 극장 위치
    hp varchar2(20) not null -- 극장 번호
);

CREATE TABLE Point (
    idx number(7) primary key,
    mem_idx number(7)   not null,
    kind varchar2(10) not null, -- 종류(use,get)에 따라 +- 결정
    tcode number(7)   not null, -- join으로 극장정보
    pointChange number(5) not null, -- 포인트 변경량
    reg_date date not null, -- 등록일
    constraint fk_point_mem_idx foreign key(mem_idx) references Member(idx),
    constraint fk_Point_tcode foreign key(tcode) references Theater (tcode),
    constraint ck_point_kind check(kind in('use','get'))
);

CREATE TABLE Movie (
    mcode number(7) primary key,
    title_ko varchar2(100)   not null, -- 제목 한글
    title_en varchar2(100) not null, -- 제목 영문
    genre varchar2(40)   not null, -- 장르
    country varchar2(20)   not null, -- 제작국가
    movie_rating varchar2(40) not null, -- 관람등급
    runtime number(3) not null, -- 러닝타임
    launch_date date not null, -- 개봉일
    reg_date date not null, -- 등록일
    poster varchar2(200) not null -- 포스터 이미지 경로
);

CREATE TABLE Hall (
    hcode number(10) primary key,
    tcode number(7)   not null, -- 극장코드
    hname varchar2(10)   not null, -- 상영관 이름
    location varchar2(10)   not null, -- 상영관 위치
    constraint fk_Hall_tcode foreign key(tcode) references Theater (tcode)
);


CREATE TABLE Wishlist (
    idx number(7) primary key,
    mcode number(7) not null, -- 영화 코드
    mem_idx number(7) not null, -- 유저 코드
    constraint fk_wishlist_mcode foreign key(mcode) references movie(mcode),
    constraint fk_wishilist_mem_idx foreign key(mem_idx) references Member(idx)
);

CREATE TABLE favCGV (
    idx number(7) primary key,
    mem_idx number(7)   not null,
    tcode number(7)   not null,
    constraint fk_favCGV_mem_idx foreign key(mem_idx) references Member(idx),
    constraint fk_favCGV_tcode foreign key(tcode) references Theater(tcode)
);

CREATE TABLE Trailer (
    idx number(7) primary key,
    mcode number(7)   not null,
    photo1 varchar2(100),
    photo2 varchar2(100),
    photo3 varchar2(100),
    photo4 varchar2(100),
    photo5 varchar2(100),
    trailer1 varchar2(100),
    trailer2 varchar2(100),
    trailer3 varchar2(100),
    constraint fk_Trailer_mcode foreign key(mcode) references Movie (mcode)
);

CREATE TABLE Actor (
    idx number(7) primary key,
    mcode number(7)   not null,
    name_ko varchar2(100)   not null, -- 배우 이름
    name_en varchar2(100)   not null, -- 배우 영문 이름
    photo varchar2(100) default 'http://img.cgv.co.kr/R2014/images/common/default_230_260.gif', 
    constraint fk_Actor_mcode foreign key(mcode) references Movie (mcode)
);

create sequence seq_actor
	increment by 1
    start with 1;

CREATE TABLE Director (
    idx number(7) primary key,
    mcode number(7)   not null,
    name_ko varchar2(50)   not null, -- 감독 이름
    name_en varchar2(50)  not null, -- 감독 영문 이름
    photo varchar2(100) default 'http://img.cgv.co.kr/R2014/images/common/default_230_260.gif',
    constraint fk_Director_mcode foreign key(mcode) references Movie (mcode)
);

create sequence seq_movie
	increment by 1
    start with 1;

CREATE TABLE Reply (
    idx number(7) primary key,
    mcode number(7)   not null, -- 영화코드
    mem_idx number(7)   not null, -- join으로 정보획득
    content varchar2(500) not null, -- 내용
    re_like number(6)  DEFAULT 0, -- 좋아요
    reg_date date not null, -- 등록일
    constraint fk_Reply_mcode foreign key(mcode) references Movie (mcode),
    constraint fk_Reply_mem_idx foreign key(mem_idx) references Member (idx)
);



CREATE TABLE Price (
    pcode number(7)   primary key,
    tcode number(7)   not null,
    week varchar2(10)   not null, -- 월~목 , 금~일
    slot varchar2(20)   not null, -- 모닝(06:00~), 브런치(10:01 ~)
    start_time varchar2(10)   not null, -- 06:00
    end_time varchar2(10)   not null, -- 10:00
    adult_price number(10)   not null, -- 성인 가격
    stu_price number(10)   not null, -- 학생 가격
    constraint ck_price_week check(week in('월~목','금~일')),
    constraint fk_Price_tcode foreign key(tcode) references Theater (tcode)
);


CREATE TABLE Schedule (
    schecode number(10)   primary key,
    mcode number(7)   not null,
    hcode number(10)   not null,
    scdate date   not null, -- 날짜
    sctime varchar2(10) not null, -- 시간
    constraint fk_Schedule_mcode foreign key(mcode) references Movie (mcode),
    constraint fk_Schedule_hcode foreign key(hcode) references Hall (hcode)
);

CREATE TABLE Seat (
    stcode number(10)   primary key,
    hcode number(10)   not null,
    st_num varchar2(10)   not null, -- 좌석 번호
    st_name varchar2(10)   not null, -- 좌석 이름
    disabled number(1) not null, -- 좌석 숨김기능(0,1)
    constraint ck_seat_disabled check(disabled in (0,1)),
    constraint fk_Seat_hcode foreign key(hcode) references Hall (hcode)
);


CREATE TABLE Seathtml (
    st_idx number(7) primary key,
    hcode number(10) not null,
    st_row number(2) not null, -- 19까지만 작성
    st_col varchar2(1) not null, -- 알파벳 L까지만 작성
    row_empty varchar2(10)   not null, -- n 다음 row가 비워짐
    col_empty varchar2(10)   not null, -- n 다음 col이 비워짐
    constraint ck_sh_row check(st_row>0 and st_row<20),
    constraint ck_sh_col check(ascii(st_col) >= ascii('A') and ascii(st_col) <= ascii('L')),
    constraint fk_Seathtml_hcode foreign key(hcode) references Hall (hcode)
);

CREATE TABLE Notification (
    idx number(7)   primary key,
    title varchar2(100)   not null,
    content varchar2(1000)   not null,
    category varchar2(20)   not null,
    hit number(7)  DEFAULT 0 not null,
    reg_date date not null
);


CREATE TABLE Ticket (
    ticode number(10)  primary key,
    schecode number(10)   not null, -- 상영스케줄 코드
    stcode number(10)   not null, -- 좌석 코드
    mem_idx number(7)   not null,
    constraint fk_Ticket_schecode foreign key(schecode) references Schedule (schecode),
    constraint fk_Ticket_stcode foreign key(stcode) references Seat (stcode),
    constraint fk_Ticket_mem_idx foreign key(mem_idx) references Member (idx)
);


CREATE TABLE Ticket_payment (
    tpcode number(10) primary key,
    ticode number(10)   not null,
    payType varchar2(10) not null, -- 카카오페이, 카드결제
    price varchar2(10)   not null, -- 가격
    payDate date not null, -- 결제일자
    canDate date, -- 결제 취소 일자
    usePoint varchar2(10)  DEFAULT 0 not null, -- 포인트사용
    totPrice varchar2(10)   not null, -- 총합 가격
    constraint fk_Ticket_payment_ticode foreign key(ticode) references Ticket (ticode)
);


CREATE TABLE Gift (
    gcode number(10) primary key,
    category varchar2(5)   not null, -- 분류
    title varchar2(50)   not null,-- 대이름
    gname varchar2(50)   not null, -- 소이름
    content varchar2(500), -- 상품설명
    price varchar2(50)   not null, -- 가격
    gfile varchar2(200)   not null,-- 이미지 경로
    end_month varchar2(10)   not null, -- 종료일
    mainon number(1) default 0,
    constraint ck_gift_mainon check(mainon in (1,0))
);
create table Gift_Explain(
    idx number(10) primary key,
    gcode number(10) not null,
    about varchar2(50) not null,
    constraint fk_gift_Explain_gcode foreign key(gcode) references Gift(gcode)
);

CREATE TABLE Gift_payment (
    gpcode number(14) primary key,
    mem_idx number(10)   not null,
    gcode number(10)   not null,
    reg_date date not null,
    status varchar2(10)   not null, -- 결제완료 사용 등등
    constraint fk_Gift_payment_mem_idx foreign key(mem_idx) references Member (idx),
    constraint fk_gift_gcode foreign key(gcode) references Gift(gcode)
);

create sequence seq_gift
    start with 1
    increment by 1
    maxvalue 9999999999;
    
create sequence seq_gift_explain
    start with 1
    increment by 1;

drop table gift_payment; drop table gift; drop table ticket_payment; drop table ticket; drop table notification; drop table seathtml;
drop table seat; drop table schedule;drop table price; drop table reply; drop table director; drop table actor; drop table trailer;
drop table favCGV; drop table wishlist;drop table hall;drop table movie; drop table point; drop table theater;  drop table member;

drop sequence seq_gift; drop sequence seq_gift_explain;

select * from gift;

-- 실행해보자


