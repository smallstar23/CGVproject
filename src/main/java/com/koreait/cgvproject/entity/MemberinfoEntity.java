package com.koreait.cgvproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name="seq_Member_idx",
        sequenceName = "seq_Member_idx",
        initialValue = 1,
        allocationSize = 1
)

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Member")
public class MemberinfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Member_idx")
    private Long idx;

    private String userid;
    private String username;
    private String userpw;
    private String ssn;
//    private String ssn2;
    private String hp;
    private String email;
//    private String address1;
//    private String address2;
//    private String address3;
    @CreatedDate
    private LocalDateTime regdate;
    private String nickname;
    private int valpoint;

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegdate() {
        return regdate;
    }

    public void setRegdate(LocalDateTime regdate) {
        this.regdate = regdate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getValpoint() {
        return valpoint;
    }

    public void setValpoint(int valpoint) {
        this.valpoint = valpoint;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}

