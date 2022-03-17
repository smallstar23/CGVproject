package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.PointDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "member")
@Builder
@SequenceGenerator(
        name="seq_point",
        sequenceName = "seq_point",
        initialValue = 1,
        allocationSize = 1
)
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_point")
    private Long idx;

    private Long pcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_idx")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tcode")
    private Theater theater;

    private String kind;
    private Long valPoint;
    private Long pointChange;
    private LocalDateTime regDate;

    public PointDTO toDTO(){
        return PointDTO.builder()
                .mem_idx(member.getIdx()).tcode(theater.getTcode()).kind(kind).pointChange(pointChange)
                .valPoint(valPoint).regDate(regDate).build();
    }
}
