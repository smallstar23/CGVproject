package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.MemberinfoEntity;
import lombok.Data;


import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

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

    public Member_info_DTO(String userid, String username, String userpw, String ssn1, String ssn2, String hp, String email, String address1, String address2, String address3, LocalDateTime regdate, LocalDateTime updateDate, Long valpoint) {
        this.userid = userid;
        this.username = username;
        this.userpw = userpw;
        this.ssn1 = ssn1;
        this.ssn2 = ssn2;
        this.hp = hp;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.regdate = regdate;
        this.updateDate = updateDate;
        this.valpoint = valpoint;
    }

    @Override
    public String toString() {
        return "Member_info_DTO{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", userpw='" + userpw + '\'' +
                ", ssn1='" + ssn1 + '\'' +
                ", ssn2='" + ssn2 + '\'' +
                ", hp='" + hp + '\'' +
                ", email='" + email + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", regdate=" + regdate +
                ", updateDate=" + updateDate +
                ", valpoint=" + valpoint +
                '}';
    }


    public MemberinfoEntity toEntity(){
        return new MemberinfoEntity(null, userid,username,userpw,ssn1,ssn2,hp,email,address1,address2,address3,regdate,updateDate,valpoint);
    }


}
