package com.koreait.cgvproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name="seq_member_info_idx",
        sequenceName = "seq_member_info_idx",
        initialValue = 1,
        allocationSize = 1
)

@Table(name="cgv_member_info")
public class MemberinfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member_info_idx")
    private Long idx;

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


    public MemberinfoEntity(Long idx, String userid, String username, String userpw, String ssn1, String ssn2, String hp, String email, String address1, String address2, String address3, LocalDateTime regdate, LocalDateTime updateDate, Long valpoint) {
        this.idx = idx;
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
        return "MemberinfoEntity{" +
                "idx=" + idx +
                ", userid='" + userid + '\'' +
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

    public MemberinfoEntity() {

    }
}
