package com.koreait.cgvproject.model.entity;

import com.koreait.cgvproject.model.dto.MemberDTO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SequenceGenerator(
        name="seq_Member",
        sequenceName = "seq_Member",
        initialValue = 1,
        allocationSize = 1
)

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Member")
@Builder
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Member")
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
    private LocalDateTime regDate;
    private String nickname;
    private int valpoint;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "member")
    private List<GiftPayment> giftPayments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Point> points;

    public Member(Long o, String userid, String username, String userpw, String s, String s1, String s2, LocalDateTime now, String nickname, int i) {
        this.idx = o;
        this.userid = userid;
        this.username = username;
        this.userpw = userpw;
        this.ssn = s;
        this.hp = s1;
        this.email = s2;
        this.regDate = now;
        this.nickname = nickname;
        this.valpoint = i;
    }

    public MemberDTO toDTO(){
         return  MemberDTO.builder()
                 .idx(idx)
                 .userid(userid).username(username).userpw(userpw)
                 .ssn1(ssn).hp1(hp).email1(email).nickname(nickname)
                 .regDate(LocalDateTime.now()).build();
    }
}

