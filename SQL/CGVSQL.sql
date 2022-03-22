CREATE TABLE Member (
    idx number(7) primary key,
    userid varchar2(20) unique not null,
    userpw varchar2(20)   not null,
    username varchar2(10)   not null,
    ssn varchar2(6)   not null, -- 주민등록번호 앞자리
    hp varchar2(13)   not null,
    email varchar2(50)   not null,
    nickname varchar2(20)   not null,
    valPoint number(10) DEFAULT 0 not null, -- 사용가능한 포인트
    reg_date date not null
);
create sequence seq_member
    start with 1
    increment by 1;

CREATE TABLE Theater (
    tcode number(7) primary key,
    tname varchar2(30) not null, -- 극장이름 강남CGV
    areacode number(2) not null,
    location varchar2(200) not null, -- 극장 위치
    hp varchar2(20) not null, -- 극장 번호
    photo varchar2(100) not null
);
create sequence seq_theater
    increment by 1
    start with 1;

CREATE TABLE Point (
    idx number(7) primary key,
    mem_idx number(7)   not null,
    kind varchar2(10) not null, -- 종류(use,get)에 따라 +- 결정
    tcode number(7)   not null, -- join으로 극장정보
    valPoint number(10) DEFAULT 0, -- 사용가능한 포인트
    pointChange number(5) not null, -- 포인트 변경량
    reg_date date not null, -- 등록일
    constraint fk_point_mem_idx foreign key(mem_idx) references Member(idx),
    constraint fk_Point_tcode foreign key(tcode) references Theater (tcode),
    constraint ck_point_kind check(kind in('use','get'))
);
create sequence seq_point
    start with 1
    increment by 1;

CREATE TABLE Movie (
    mcode number(7) primary key,
    mscreen varchar2(100)  default '상영중', --상영여부(상영중,상영예정,종영)
    title_ko varchar2(100)   not null, -- 제목 한글
    title_en varchar2(100) not null, -- 제목 영문
    genre varchar2(40)   not null, -- 장르
    country varchar2(20)   not null, -- 제작국가
    movie_rating varchar2(40) not null, -- 관람등급
    runtime number(3) not null, -- 러닝타임
    launch_date date not null, -- 개봉일
    reg_date date not null, -- 등록일
    poster varchar2(500) not null -- 포스터 이미지 경로
);
create sequence seq_movie
	increment by 1
    start with 1;

CREATE TABLE Hall (

   hcode number(10) not null primary key,
   hguan number(10) not null, -- 2관
   tcode number(7) not null,  -- 극장 코드
   hname varchar2(100), -- 상영관 이름 (name그대로 집어넣으면 오류납니다.)
   location varchar2(100)   not null, -- 상영관 위치
   constraint fk_Hall_tcode foreign key(tcode) references Theater (tcode)
);
create sequence seq_hall
    increment by 1
    start with 1;

CREATE TABLE favCGV (
    idx number(7) primary key,
    mem_idx number(7)   not null,
    tcode number(7)   not null,
    constraint fk_favCGV_mem_idx foreign key(mem_idx) references Member(idx),
    constraint fk_favCGV_tcode foreign key(tcode) references Theater(tcode)
);
create sequence seq_favCGV
    increment by 1
    start with 1;

CREATE TABLE Trailer (
     idx number(7) primary key,
     mcode number(7)   not null,
     description1 varchar2(500),
     description2 varchar2(500),
     description3 varchar2(500),
     photo1 varchar2(300),
     photo2 varchar2(300),
     photo3 varchar2(300),
     photo4 varchar2(300),
     photo5 varchar2(300),
     trailer1 varchar2(200),
     trailer2 varchar2(200),
     trailer3 varchar2(200),
     constraint fk_Trailer_mcode foreign key(mcode) references Movie (mcode)
);
create sequence seq_trailer
    increment by 1
    start with 1;

CREATE TABLE Actor (
    idx number(7) primary key,
    mcode number(7)   not null,
    aname_ko varchar2(100)   not null, -- 배우 이름
    aname_en varchar2(100)   not null, -- 배우 영문 이름
    aphoto varchar2(500) default 'http://img.cgv.co.kr/R2014/images/common/default_230_260.gif',
    constraint fk_Actor_mcode foreign key(mcode) references Movie (mcode)
);
create sequence seq_actor
	increment by 1
    start with 1;

CREATE TABLE Director (
    idx number(7) primary key,
    mcode number(7)   not null,
    dname_ko varchar2(50)   not null, -- 감독 이름
    dname_en varchar2(50)  not null, -- 감독 영문 이름
    dphoto varchar2(500) default 'http://img.cgv.co.kr/R2014/images/common/default_230_260.gif',
    constraint fk_Director_mcode foreign key(mcode) references Movie (mcode)
);
create sequence seq_director
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
create sequence seq_reply
    increment by 1
    start with 1;



CREATE TABLE Price (
    pcode number(7)   primary key,
    tcode number(7) not null,
    week varchar2(10)   not null, -- 월~목 , 금~일
    slot varchar2(20)   not null, -- 모닝(06:00~), 브런치(10:01 ~)
    start_time varchar2(10)   not null, -- 06:00
    end_time varchar2(10)   not null, -- 10:00
    adult_price varchar2(10)   not null, -- 성인 가격
    stu_price varchar2(10)   not null, -- 학생 가격
    constraint ck_price_week check(week in('월~목','금~일')),
    constraint fk_Price_tcode foreign key(tcode) references Theater (tcode)
);
create sequence seq_price
    start with 1
    increment by 1;


CREATE TABLE Schedule (
  schecode number(10)   primary key,
  mcode number(7)   not null,
  hcode number(10)   not null,
  scdate date   not null, -- 날짜, 시간까지 같이 저장할 것
  constraint fk_Schedule_mcode foreign key(mcode) references Movie (mcode),
  constraint fk_Schedule_hcode foreign key(hcode) references Hall (hcode)
);
create sequence seq_schedule
    increment by 1
    start with 1;
    
--insert into schedule(schecode, mcode, hcode, scdate) values (seq_schedule.nextval, 64, 46, TO_DATE('2022/03/14 21:00:00','YYYY-MM-DD HH24:MI:SS'));
--ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD HH24:MI:SS';

CREATE TABLE Seat (
    stcode number(10)   primary key,
    hcode number(10) not null,
    st_num varchar2(10) not null, -- 좌석 번호
    disabled number(1) not null, -- 좌석 숨김기능(0,1)
    constraint ck_seat_disabled check(disabled in (0,1)),
    constraint fk_Seat_hcode foreign key(hcode) references Hall (hcode)
);
create sequence seq_seat
    start with 1
    increment by 1;

CREATE TABLE Seathtml (
    st_idx number(7) primary key,
    hcode number(10) not null,
    st_row varchar(2) not null, -- 알파벳 Z
    st_col varchar2(2) not null, -- 
    row_empty varchar2(40) default 0 not null, -- n 다음 row가 비워짐
    col_empty varchar2(40) default 0 not null, -- n 다음 col이 비워짐
    constraint ck_sh_col check(ascii(st_row) >= ascii('A') and ascii(st_row) <= ascii('Z')),
    constraint fk_Seathtml_hcode foreign key(hcode) references Hall (hcode)
);

create sequence seq_seathtml
    start with 1
    increment by 1;

CREATE TABLE Notification (
    idx number(7)   primary key,
    title varchar2(100)   not null,
    content varchar2(10000)   not null,
    category varchar2(20)   not null,
    hit number(7)  DEFAULT 0 not null,
    reg_date date not null
);
create sequence seq_notification
    increment by 1
    start with 1;

CREATE TABLE Ticket (
                        ticode number(10)  primary key,
                        schecode number(10)   not null, -- 상영스케줄 코드
                        totperson number(10) not null, -- 예매한 인원
                        seat varchar2(20)   not null, -- 예매한 좌석 (A1,A2)
                        mem_idx number(7)   not null,
                        price varchar2(10)   not null, -- 가격
                        payDate date not null, -- 결제일자
                        canDate date, -- 결제 취소 일자
                        usePoint varchar2(10)  DEFAULT 0 not null, -- 포인트사용
                        totPrice varchar2(10)   not null, -- 총합 가격

                        constraint fk_Ticket_schecode foreign key(schecode) references Schedule (schecode),
                        constraint fk_Ticket_mem_idx foreign key(mem_idx) references Member (idx)
);


create sequence seq_ticket
    increment by 1
    start with 1;

-- 위 ticket과 합침
--CREATE TABLE Ticket_payment (
--    tpcode number(10) primary key,
--    ticode number(10)   not null,
--    payType varchar2(10) not null, -- 카카오페이, 카드결제
--    price varchar2(10)   not null, -- 가격
--    payDate date not null, -- 결제일자
--    canDate date, -- 결제 취소 일자
--    usePoint varchar2(10)  DEFAULT 0 not null, -- 포인트사용
--    totPrice varchar2(10)   not null, -- 총합 가격
--    constraint fk_Ticket_payment_ticode foreign key(ticode) references Ticket (ticode)
--);
--create sequence seq_ticket_payment
--    increment by 1
--    start with 1;

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
    mainon_string varchar2(100) default '없음'
);
create sequence seq_gift
    start with 1
    increment by 1;

CREATE TABLE Gift_payment (
    gpcode number(14) primary key,
    mem_idx number(10)   not null,
    gcode number(10)   not null,
    reg_date date not null,
    status varchar2(10)   not null, -- 결제완료 사용 등등
    constraint fk_Gift_payment_mem_idx foreign key(mem_idx) references Member (idx),
    constraint fk_gift_gcode foreign key(gcode) references Gift(gcode)
);
create sequence seq_gift_payment
    increment by 1
    start with 1;


drop table gift_payment;  drop table gift; drop table ticket_payment; drop table ticket; drop table notification; drop table seathtml;
drop table seat; drop table schedule; drop table price; drop table reply; drop table director; drop table actor; drop table trailer;
drop table favCGV; drop table hall; drop table movie; drop table point; drop table theater;  drop table member;


drop sequence seq_gift_payment; drop sequence seq_gift; drop sequence seq_actor;
drop sequence seq_director; drop sequence seq_favCGV; drop sequence seq_hall;
drop sequence seq_member; drop sequence seq_movie; drop sequence seq_notification;
drop sequence seq_point; drop sequence seq_price; drop sequence seq_reply;
drop sequence seq_schedule; drop sequence seq_trailer; drop sequence seq_ticket_payment;
drop sequence seq_ticket; drop sequence seq_theater; drop sequence seq_seathtml;
drop sequence seq_seat;

commit; -- 데이터를 잘 넣어놨는데도 안뜬다. 누르셈


-- 있다 없어진 테이블 삭제 --
drop table gift_Explain;
drop table wishlist;

drop sequence seq_gift_explain;
-- 있다 없어진 테이블 삭제 끝 --

-- 멤버, 공지 시퀀스(태훈)

create sequence seq_Member
    increment by 1
    start with 1;

create sequence seq_Notification
    increment by 1
    start with 1;

---------------------------------

insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'2','CGV 영화관람권','일반 영화관람권','CGV 영화관람권으로 즐거운 영화관람하세요!','11,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/16094706931690.jpg','24',1,'즐거운 경험은 CGV에서!');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'2','CGV 골드클래스','골드클래스 관람권','최고의 관람환경을 제공함과 동시에 고객 맞춤형
서비스까지 제공되는 프리미엄 상영관입니다.','35,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15458911955580.jpg','24',2,'최고의 관람환경을 제공하는 프리미엄 상영관');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'2','CGV 씨네드쉐프 관람권','씨네드쉐프 관람권','특별한 초대의 시간에 진정한 품격을 더합니다.
꿈이 현실로 이루어지는 씨네드쉐프에서
소중한 분들과 함께 최고의 가치를 경험하십시오.','45,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15458907785230.jpg','24');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'2','4DX관람권','4DX 영화관람권','단순한 영화 관람을 넘어 영화 속 주인공이 된 듯한 체험을 해보세요!
장면마다 생생함을 극대화시키는 4DX 효과를 통해 영화에 더욱 몰입할 수 있습니다.','19,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/16105061094000.jpg','24',3,'오감만족 영화 속 주인공 되기');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'2','IMAX 관람권','IMAX 영화관람권','IMAX는 사람이 볼 수 있는 최대 영상이라는 뜻으로, 최고의 몰입감을 선사합니다.','16,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/16104445892120.jpg','24',4,'사람이 볼 수 있는 최대 영상, IMAX');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'4','CGV콤보','팝콘(L)1+탄산음료(M)2','CGV의 영원한 베스트셀러!','9,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463252014310.jpg','6',1,'CGV의 영원한 베스트셀러!');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'4','더블콤보','팝콘(M)2+탄산음료(M)2','취향별로 원하는 맛 선택하세요!','12,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463251542560.jpg','6',2,'취향별로 원하는 맛 선택하세요!');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'4','스몰세트','팝콘(M)1+탄산음료(M)1','혼영할때 필수품! 잊지마세요','6,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464065566830.jpg','6',3,'혼영할때 필수품');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'4','라지콤보','팝콘(L)2+탄산음료(L)2','가성비 최고 콤보!
여럿이 함께할 때 라지콤보로 맛있는 영화 경험 하세요','14,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463216258050.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'5','고소팝콘(L)','고소팝콘(L)','옥수수 본연의 맛을 즐길 수 있는 짭짜름한 클래식 고소팝콘!','5,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463244802080.jpg','6',1,'클래식 팝콘 No.1');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'5','달콤팝콘(L)','달콤팝콘(L)','달콤한 카라멜 향이 가득한 달콤팝콘을 즐겨보세요!','6,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463244218630.jpg','6',2,'달콤한 카라멜 향이 가득한 달콤팝콘');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'5','더블치즈팝콘(L)','더블치즈팝콘(L)','치즈매니아들이라면 놓칠 수 없는 바로 그 팝콘!','6,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463247060090.jpg','6',3,'치즈매니아들 주목!');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','바질어니언팝콘(L)','바질어니언팝콘(L)','수많은 매니아를 보유한 바로 그 팝콘! 중독성 200%','6,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463246055890.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','바질어니언팝콘(M)','바질어니언팝콘(M)','수많은 매니아를 보유한 바로 그 팝콘! 중독성 200%','5,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464079703730.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','더블치즈팝콘(M)','더블치즈팝콘(M)','치즈매니아들이라면 놓칠 수 없는 바로 그 팝콘!','5,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464078827160.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','고소팝콘(M)','고소팝콘(M)','옥수수 본연의 맛을 즐길 수 있는 짭짜름한 클래식 고소팝콘!','4,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463243503300.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','달콤팝콘(M)','달콤팝콘(M)','달콤한 카라멜 향이 가득한 달콤팝콘을 즐겨보세요!','5,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463242306110.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'6','탄산음료(M)','탄산음료(M)','콜라,콜라제로,환타오렌지,환타포도,스프라이트 중 원하는 맛으로 교환하세요!','2,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464083673670.jpg','6',1,'시원한 탄산음료와 함께 스트레스도 날리세요');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'6','아메리카노(HOT)','아메리카노(HOT)','현대인의 필수품, 아메리카노 한잔하세요~','3,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464096045540.jpg','6',2,'현대인의 필수품');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'6','아메리카노(ICE)','아메리카노(ICE)','현대인의 필수품, 아메리카노 한잔하세요~','4,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464105897860.jpg','6',3,'현대인의 필수품');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'6','탄산음료(L)','탄산음료(L)','콜라,콜라제로,환타오렌지,환타포도,스프라이트 중 원하는 맛으로 교환하세요!','3,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464084831410.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'7','칠리치즈나쵸','칠리치즈나쵸','바삭바삭한 나쵸에 칠리치즈의 조합, 여기가 멕시코인가요?','4,900','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15459092403030.jpg','6',1,'바삭바삭 나쵸, 얼마나 맛있게요?');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'7','플레인핫도그','플레인핫도그','플레인핫도그','4,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464120861130.jpg','6',2,'플레인핫도그');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'7','칠리치즈핫도그','칠리치즈핫도그','칠리치즈핫도그','5,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464115505070.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'7','맛밤','맛밤','맛도 있고 건강에도 좋은 맛밤','3,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464178224220.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month, mainon, mainon_string) values (seq_gift.nextval,'7','오징어(완제품)','오징어(완제품)','영화 보는 즐거움에 씹는 즐거움까지 더해보세요!','3,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15459089336130.jpg','6',3,'한봉지로는 모자라요');


insert into Movie (mcode, mscreen, title_ko, title_en, genre, country, movie_rating, runtime, launch_date, reg_date, poster) values (seq_movie.nextval, '상영중', '더 배트맨', 'The Batman', '액션', '미국', '15세 이상', 176, '20220301', sysdate, 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000085/85632/85632_1000.jpg');
insert into Movie (mcode, mscreen, title_ko, title_en, genre, country, movie_rating, runtime, launch_date, reg_date, poster) values (seq_movie.nextval, '상영중', '이상한 나라의 수학자', 'In Our Prime', '드라마', '한국', '12세 이상', 117, '20220309', sysdate, 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000085/85666/85666_1000.jpg');
insert into Movie (mcode, mscreen, title_ko, title_en, genre, country, movie_rating, runtime, launch_date, reg_date, poster) values (seq_movie.nextval, '상영중', '나의 히어로 아카데미아 더 무비 월드 히어로즈 미션', 'My Hero Academia World Heroes Mission', '애니메이션', '일본', '12세 이상', 104, '20220309', sysdate, 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000085/85684/85684_1000.jpg');


insert into theater(tcode, areacode, tname, location, hp, photo) values (seq_theater.nextval, 01, '강남CGV', '서울특별시 강남구 역삼동 814-6 스타플렉스', '1544-1122','https://img.cgv.co.kr/Theater/Theater/2014/1211/CGVgangnam.jpg' );
insert into theater(tcode, areacode, tname, location, hp, photo) values (seq_theater.nextval, 01, '강변CGV', '서울특별시 광진구 구의동 546-4 테크노마트 10층', '1544-1122', 'https://img.cgv.co.kr/Theater/Theater/2019/0107/15468415578940.png');
insert into theater(tcode, areacode, tname, location, hp, photo) values (seq_theater.nextval, 02, '경기광주CGV', '경기도 광주시 역동16-1 2층', '1544-1122','https://img.cgv.co.kr/Theater/Theater/2018/0309/15205836456080.jpg');
insert into theater(tcode, areacode, tname, location, hp, photo) values (seq_theater.nextval, 02, '동수원CGV', '경기도 수원시 팔달구 인계동 1113-11,12 씨네파크 7층', '1544-1122','https://img.cgv.co.kr/Theater/Theater/2019/1210/15759380284040.jpg');
insert into theater(tcode, areacode, tname, location, hp, photo) values (seq_theater.nextval, 02, '용인CGV', '경기도 용인시 처인구 김량장동 301-3, 5층', '1544-1122','https://img.cgv.co.kr/Theater/Theater/2018/0330/15224028011320.jpg');

insert into trailer(idx, mcode, description1, description2, description3, photo1, photo2, photo3, photo4, photo5, trailer1, trailer2, trailer3)
values (seq_trailer.nextval, 1, '지난 2년간 고담시의 어둠 속에서 범법자들을 응징하며 배트맨으로 살아온 브루스 웨인. 알프레드와 제임스 고든 경위의 도움 아래, 도시의 부패한 공직자들과 고위 관료들 사이에서 복수의 화신으로 활약한다. ', '고담의 시장 선거를 앞두고 고담의 엘리트 집단을 목표로 잔악한 연쇄살인을 저지르는 수수께끼 킬러 리들러가 나타나자, 최고의 탐정 브루스 웨인이 수사에 나서고 남겨진 단서를 풀어가며 캣우먼, 펭귄, 카마인 팔코네, 리들러를 차례대로 만난다.  사이코 범인의 미스터리를 수사하면서 그 모든 증거가 자신을 향한 의도적인 메시지였음을 깨닫고,','리들러에게 농락 당한 배트맨은 광기에 사로잡힌다. 범인의 무자비한 계획을 막고 오랫동안 고담시를 썩게 만든 권력 부패의 고리를 끊어야 하지만,  부모님의 죽음에 얽힌 진실이 밝혀지자 복수와 정의 사이에서 갈등한다.',
'https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000085/85632/85632200251_727.jpg','https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000085/85632/85632200250_727.jpg'
,'https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000085/85632/85632200249_727.jpg','https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000085/85632/85632200248_727.jpg','https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000085/85632/85632200121_727.jpg'
,'http://h.vod.cgv.co.kr/vodCGVa/85632/85632_199767_1200_128_960_540.mp4', 'http://h.vod.cgv.co.kr/vodCGVa/85632/85632_199700_1200_128_960_540.mp4',
'http://h.vod.cgv.co.kr/vodCGVa/85632/85632_199699_1200_128_960_540.mp4');

insert into trailer(idx, mcode, description1, description2, description3, photo1, photo2, photo3, photo4, photo5, trailer1, trailer2, trailer3)
values (seq_trailer.nextval, 2, '학문의 자유를 갈망하며 탈북한 천재 수학자 이학성(최민식). 그는 자신의 신분과 사연을 숨긴 채 상위 1%의 영재들이 모인 자사고의 경비원으로 살아간다. ',
'차갑고 무뚝뚝한 표정으로 학생들의 기피 대상 1호인 이학성은 어느 날 자신의 정체를 알게 된 뒤 수학을 가르쳐 달라 조르는 수학을 포기한 고등학생 한지우(김동휘)를 만난다. ',
'정답만을 찾는 세상에서 방황하던 ‘한지우’에게 올바른 풀이 과정을 찾아나가는 법을 가르치며 ‘이학성’ 역시 뜻하지 않은 삶의 전환점을 맞게 된다.',
'https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000085/85666/85666200778_727.jpg'
,'https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000085/85666/85666200777_727.jpg'
,'https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000085/85666/85666200614_727.jpg'
,'https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000085/85666/85666200342_727.jpg'
,'https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000085/85666/85666200338_727.jpg',
'http://h.vod.cgv.co.kr/vodCGVa/85666/85666_200801_1200_128_960_540.mp4',
'http://h.vod.cgv.co.kr/vodCGVa/85666/85666_200317_1200_128_960_540.mp4'
,'http://h.vod.cgv.co.kr/vodCGVa/85666/85666_200474_1200_128_960_540.mp4');
