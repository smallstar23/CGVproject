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
    private String hp;
    private String email;
    private String address1;
    private String address2;
    private String address3;
    private LocalDateTime regdate;
    private LocalDateTime updateDate;
    private Long valpoint;


    public MemberinfoEntity toEntity(){
        return new MemberinfoEntity(null, userid,username,userpw,ssn1,ssn2,hp,email,address1,address2,address3,regdate,updateDate,valpoint);
    }


}
