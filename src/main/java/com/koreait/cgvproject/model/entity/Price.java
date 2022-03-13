package com.koreait.cgvproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SequenceGenerator(
        name = "seq_price",
        sequenceName = "seq_price",
        initialValue = 1,
        allocationSize = 1
)
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_price")
    private Long pcode;
    private String week; // 월~목,  금~일 check제약조건 걸려있음
    private String slot; // 모닝(06:00~), 브런치(10:01~)
    private String startTime; // 06:00
    private String endTime; // 10:00
    private String adultPrice;
    private String stuPrice;

    @OneToOne
    private Theater theater;
}
