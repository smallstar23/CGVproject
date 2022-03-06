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
    regDate date not null
);

create sequence seq_Member_idx
    increment by 1
    start with 1;

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
    regDate date not null, -- 등록일
    constraint fk_point_mem_idx foreign key(mem_idx) references Member(idx),
    constraint fk_Point_tcode foreign key(tcode) references Theater (tcode),
    constraint ck_point_kind check(kind in('use','get'))
);

CREATE TABLE Movie (
    mcode number(7) primary key,
    title varchar2(100)   not null,
    genre varchar2(40)   not null, -- 장르
    makeDate varchar2(30)   not null, -- 제작일
    country varchar2(20)   not null, -- 제작국가
    movieRating varchar2(10) not null, -- 관람등급
    runtime number(4) not null, -- 러닝타임
    launchDate date not null, -- 개봉일
    endDate date not null, -- 종영일
    regDate date not null, -- 등록일
    poster varchar2(20) not null -- 포스터 이미지 경로
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
    aname varchar2(100)   not null, -- 배우 이름
    name_en varchar2(100)   not null, -- 배우 영문 이름
    photo varchar2(100) default '없음', 
    constraint fk_Actor_mcode foreign key(mcode) references Movie (mcode)
);

CREATE TABLE Director (
    idx number(7) primary key,
    mcode number(7)   not null,
    dname varchar2(50)   not null, -- 감독 이름
    name_en varchar2(50)  not null, -- 감독 영문 이름
    photo varchar2(100) default '없음',
    constraint fk_Director_mcode foreign key(mcode) references Movie (mcode)
);

CREATE TABLE Reply (
    idx number(7) primary key,
    mcode number(7)   not null, -- 영화코드
    mem_idx number(7)   not null, -- join으로 정보획득
    content varchar2(500) not null, -- 내용
    re_like number(6)  DEFAULT 0, -- 좋아요
    regDate date not null, -- 등록일
    constraint fk_Reply_mcode foreign key(mcode) references Movie (mcode),
    constraint fk_Reply_mem_idx foreign key(mem_idx) references Member (idx)
);



CREATE TABLE Price (
    pcode number(7)   primary key,
    tcode number(7)   not null,
    week varchar2(10)   not null, -- 월~목 , 금~일
    slot varchar2(20)   not null, -- 모닝(06:00~), 브런치(10:01 ~)
    startTime varchar2(10)   not null, -- 06:00
    endTime varchar2(10)   not null, -- 10:00
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
    num varchar2(10)   not null, -- 좌석 번호
    name varchar2(10)   not null, -- 좌석 이름
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
    regDate date not null
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
    end_month varchar2(10)   not null -- 종료일
);

CREATE TABLE Gift_payment (
    gpcode number(14) primary key,
    mem_idx number(10)   not null,
    gcode number(10)   not null,
    regDate date not null,
    status varchar2(10)   not null, -- 결제완료 사용 등등
    constraint fk_Gift_payment_mem_idx foreign key(mem_idx) references Member (idx),
    constraint fk_gift_gcode foreign key(gcode) references Gift(gcode)
);

create sequence seq_gift
    start with 1
    increment by 1
    maxvalue 9999999999;
    


drop table gift_payment; drop table gift; drop table ticket_payment; drop table ticket; drop table notification; drop table seathtml;
drop table seat; drop table schedule;drop table price; drop table reply; drop table director; drop table actor; drop table trailer;
drop table favCGV; drop table wishlist;drop table hall;drop table movie; drop table point; drop table theater;  drop table member; drop sequence seq_gift;

select * from gift;

-- 실행해보자

insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'2','CGV 영화관람권','일반 영화관람권','CGV 영화관람권으로 즐거운 영화관람하세요!','11,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/16094706931690.jpg','24');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'2','CGV 골드클래스','골드클래스 관람권','최고의 관람환경을 제공함과 동시에 고객 맞춤형
서비스까지 제공되는 프리미엄 상영관입니다.','35,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15458911955580.jpg','24');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'2','CGV 씨네드쉐프 관람권','씨네드쉐프 관람권','특별한 초대의 시간에 진정한 품격을 더합니다.
꿈이 현실로 이루어지는 씨네드쉐프에서
소중한 분들과 함께 최고의 가치를 경험하십시오.','45,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15458907785230.jpg','24');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'2','4DX관람권','4DX 영화관람권','단순한 영화 관람을 넘어 영화 속 주인공이 된 듯한 체험을 해보세요!
장면마다 생생함을 극대화시키는 4DX 효과를 통해 영화에 더욱 몰입할 수 있습니다.','19,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/16105061094000.jpg','24');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'2','IMAX 관람권','IMAX 영화관람권','IMAX는 사람이 볼 수 있는 최대 영상이라는 뜻으로, 최고의 몰입감을 선사합니다.','16,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/16104445892120.jpg','24');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'4','CGV콤보','팝콘(L)1+탄산음료(M)2','CGV의 영원한 베스트셀러!','9,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463252014310.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'4','더블콤보','팝콘(M)2+탄산음료(M)2','취향별로 원하는 맛 선택하세요!','12,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463251542560.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'4','스몰세트','팝콘(M)1+탄산음료(M)1','혼영할때 필수품! 잊지마세요','6,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464065566830.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'4','라지콤보','팝콘(L)2+탄산음료(L)2','가성비 최고 콤보!
여럿이 함께할 때 라지콤보로 맛있는 영화 경험 하세요','14,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463216258050.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','고소팝콘(L)','고소팝콘(L)','옥수수 본연의 맛을 즐길 수 있는 짭짜름한 클래식 고소팝콘!','5,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463244802080.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','달콤팝콘(L)','달콤팝콘(L)','달콤한 카라멜 향이 가득한 달콤팝콘을 즐겨보세요!','6,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463244218630.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','더블치즈팝콘(L)','더블치즈팝콘(L)','치즈매니아들이라면 놓칠 수 없는 바로 그 팝콘!','6,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463247060090.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','바질어니언팝콘(L)','바질어니언팝콘(L)','수많은 매니아를 보유한 바로 그 팝콘! 중독성 200%','6,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463246055890.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','바질어니언팝콘(M)','바질어니언팝콘(M)','수많은 매니아를 보유한 바로 그 팝콘! 중독성 200%','5,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464079703730.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','더블치즈팝콘(M)','더블치즈팝콘(M)','치즈매니아들이라면 놓칠 수 없는 바로 그 팝콘!','5,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464078827160.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','고소팝콘(M)','고소팝콘(M)','옥수수 본연의 맛을 즐길 수 있는 짭짜름한 클래식 고소팝콘!','4,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463243503300.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'5','달콤팝콘(M)','달콤팝콘(M)','달콤한 카라멜 향이 가득한 달콤팝콘을 즐겨보세요!','5,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15463242306110.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'6','탄산음료(M)','탄산음료(M)','콜라,콜라제로,환타오렌지,환타포도,스프라이트 중 원하는 맛으로 교환하세요!','2,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464083673670.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'6','아메리카노(HOT)','아메리카노(HOT)','현대인의 필수품, 아메리카노 한잔하세요~','3,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464096045540.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'6','아메리카노(ICE)','아메리카노(ICE)','현대인의 필수품, 아메리카노 한잔하세요~','4,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464105897860.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'6','탄산음료(L)','탄산음료(L)','콜라,콜라제로,환타오렌지,환타포도,스프라이트 중 원하는 맛으로 교환하세요!','3,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464084831410.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'7','칠리치즈나쵸','칠리치즈나쵸','바삭바삭한 나쵸에 칠리치즈의 조합, 여기가 멕시코인가요?','4,900','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15459092403030.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'7','플레인핫도그','플레인핫도그','플레인핫도그','4,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464120861130.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'7','칠리치즈핫도그','칠리치즈핫도그','칠리치즈핫도그','5,000','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464115505070.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'7','맛밤','맛밤','맛도 있고 건강에도 좋은 맛밤','3,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15464178224220.jpg','6');
insert into Gift (gcode, category, title, gname, content, price, gfile, end_month) values (seq_gift.nextval,'7','오징어(완제품)','오징어(완제품)','영화 보는 즐거움에 씹는 즐거움까지 더해보세요!','3,500','http://img.cgv.co.kr/GiftStore/Product/Pc/Detail/15459089336130.jpg','6');

set autocommit on;

