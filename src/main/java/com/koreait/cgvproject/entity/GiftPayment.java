package com.koreait.cgvproject.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(
        name = "seq_gift_payment",
        sequenceName = "seq_gift_payment",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@ToString(exclude = "member")
//@ToString(exclude = "member")
//@ToString(exclude = "gift")
@EntityListeners(AuditingEntityListener.class)
@Table(name="Gift_payment")
public class GiftPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gift_payment")
    private Long gpcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_idx") // mem_idx 컬럼에 mem_idx 대표값 저장
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gcode") // gcode 컬럼에 gcode의 대표값 저장
    private Gift gift;

//    private Long memIdx;
//    private Long gcode;

    @CreatedDate
    private LocalDateTime regDate;
    private String status;

}
