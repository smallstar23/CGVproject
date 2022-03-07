package com.koreait.cgvproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {

    @Id
    private Long pcode;
    private String week;
    private String slot;
    private String startTime;
    private String endTime;
    private Integer adultPrice;
    private Integer stuPrice;

//    private Theater theater;
}
