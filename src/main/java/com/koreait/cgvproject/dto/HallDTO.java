package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Hall;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HallDTO{

    private Long hcode;
    private Long tcode;
    private String hname;
    private String location;

    public Hall toEntity() {
        Hall hall = Hall.builder()
                .hcode(hcode)
                .tcode(tcode)
                .hname(hname)
                .location(location)
                .build();
        return hall;
    }
}
