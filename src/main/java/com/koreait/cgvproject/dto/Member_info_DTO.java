package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.MemberinfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@AllArgsConstructor // 모든 생성자를 자동으로 만들게 해주는 롬복
@ToString // toString 쓸수있게해줌
@Data
public class Member_info_DTO {

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
    private LocalDateTime updateDate;
    private String nickname;


    public MemberinfoEntity toEntity(){
        return new MemberinfoEntity(null, userid,username,userpw,getSsn1().substring(3)+getSsn2().substring(1)+getSsn3(),getHp1() + "-" + getHp2() + "-" + getHp3(),getEmail1()+"@"+getEmail2(),regdate,updateDate,nickname);
    }


}
