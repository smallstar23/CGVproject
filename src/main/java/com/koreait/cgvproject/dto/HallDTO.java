package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Hall;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HallDTO{

    private Long hcode;
    private TheaterDTO theater;
    private Long tcode;
    private Long hguan;
    private String hname;
    private String location;

    public Hall toEntity() {
        return Hall.builder()
                .hcode(hcode)
                .theater(theater.toEntity())
                .hname(hname)
                .hguan(hguan)
                .location(location)
                .build();
    }
}
