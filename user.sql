create table cgv_member_info(
    idx number(7) not null,
    userid varchar2(20) not null,
    username varchar2(20) not null,
    userpw varchar2(20) not null,
    ssn1 varchar2(6) ,
    hp varchar2(15) not null,
    email varchar2(20) not null,
    regdate date default sysdate,
    update_date date default sysdate,
    nickname varchar(20)
);

create sequence seq_member_info_idx
    increment by 1
    start with 1
    
drop table cgv_member_info;
drop sequence seq_member_info_idx;

select * from cgv_member_info;
