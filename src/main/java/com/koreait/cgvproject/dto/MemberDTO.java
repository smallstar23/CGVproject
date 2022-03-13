package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


import java.time.LocalDateTime;

@AllArgsConstructor // 모든 생성자를 자동으로 만들게 해주는 롬복
@ToString // toString 쓸수있게해줌
@Data
@Builder
public class MemberDTO {

    private Long idx;
    private String userid;
    private String username;
    private String userpw;
    private String ssn1;
    private String ssn2;
    private String ssn3;
    private String hp1;
    private String hp2;
    private String hp3;
    private String email1;
    private String email2;
//    private String address1;
//    private String address2;
//    private String address3;
    private LocalDateTime regDate;
    private String nickname;


    public Member toadminMember(){
      Member member = Member.builder()
              .idx(idx)
              .userid(userid).userpw(userpw).username(username).ssn(ssn1+ssn2+ssn3).hp(hp1+hp2+hp3)
              .email(email1+email2).regDate(LocalDateTime.now()).nickname(nickname).valpoint(0).build();
            return member;
    }



    public Member toEntity(MemberDTO memberDTO){
        return new Member(null, userid,username,userpw,getSsn1().substring(2)+getSsn2()+getSsn3(),getHp1()
                + "-" + getHp2() + "-" + getHp3(),getEmail1()+"@"+getEmail2(),regDate.now(),nickname,0);
    }


}
