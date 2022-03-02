package com.koreait.cgvproject.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name="seq_member_info_idx",
        sequenceName = "seq_member_info_idx",
        initialValue = 1,
        allocationSize = 1
)

@AllArgsConstructor
@NoArgsConstructor
@ToString
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


}
