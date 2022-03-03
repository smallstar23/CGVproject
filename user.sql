create table cgv_member_info(
    idx number(7) not null,
    userid varchar2(20) not null,
    username varchar2(20) not null,
    userpw varchar2(20) not null,
    ssn1 varchar2(6) ,
    ssn2 varchar2(7) ,
    hp varchar2(15) not null,
    email varchar2(20) not null,
    address1 varchar2(30) not null,
    address2 varchar2(30) not null,
    address3 varchar2(30) not null,
    regdate date default sysdate,
    update_date date default sysdate,
    valpoint number(7) default 0
);

create sequence seq_member_info_idx
    increment by 1
    start with 1
    
drop table cgv_member_info;
drop sequence seq_member_info_idx;

select * from cgv_member_info;
