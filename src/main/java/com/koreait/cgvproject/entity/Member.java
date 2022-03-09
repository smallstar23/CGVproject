package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.MemberDTO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public MemberDTO toDTO(){
         return  MemberDTO.builder()
                 .idx(idx)
                 .userid(userid).username(username).userpw(userpw)
                 .ssn1(ssn).hp1(hp).email1(email).nickname(nickname)
                 .regDate(LocalDateTime.now()).build();
    }
}

