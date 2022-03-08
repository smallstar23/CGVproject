package com.koreait.cgvproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@EntityListeners(AuditingEntityListener.class)
public class GiftPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gift_payment")
    private Long gpcode;
    private String status;
    @CreatedDate
    private LocalDateTime regDate;
}
