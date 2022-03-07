package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.MemberinfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@AllArgsConstructor // 모든 생성자를 자동으로 만들게 해주는 롬복
@ToString // toString 쓸수있게해줌
@Data
@Builder
public class Member_info_DTO {

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
    private LocalDateTime regdate;
    private String nickname;


    public MemberinfoEntity toadminMember(){
      MemberinfoEntity  memberinfoEntity=MemberinfoEntity.builder()
              .idx(idx)
              .userid(userid).userpw(userpw).username(username).ssn(ssn1+ssn2+ssn3).hp(hp1+hp2+hp3)
              .email(email1+email2).regdate(LocalDateTime.now()).nickname(nickname).valpoint(0).build();
            return  memberinfoEntity;
    }



    public MemberinfoEntity toEntity(){
        return new MemberinfoEntity(null, userid,username,userpw,getSsn1().substring(2)+getSsn2()+getSsn3(),getHp1()
                + "-" + getHp2() + "-" + getHp3(),getEmail1()+"@"+getEmail2(),regdate.now(),nickname,0);
    }


}
