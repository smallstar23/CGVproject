package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Price;
import com.koreait.cgvproject.entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceDTO {

    private Long pcode;
    private String week;
    private String slot;
    private String startTime;
    private String endTime;
    private String adultPrice;
    private String stuPrice;
    private Long tcode; // theater

    public Price toEntityForCreateTheaterRequired(Theater theater){
        return Price.builder()
                .pcode(pcode)
                .week(week)
                .slot(slot)
                .startTime(startTime)
                .endTime(endTime)
                .adultPrice(adultPrice)
                .stuPrice(stuPrice)
                .theater(theater)
                .build();
    }
}
