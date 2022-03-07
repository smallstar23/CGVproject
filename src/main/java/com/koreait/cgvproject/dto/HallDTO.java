package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Hall;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HallDTO{
    private Long hcode;
    private Long tcode;
    private String hname;
    private String location;
    private LocalDateTime regdate;

    public Hall toEntity() {
        Hall hall = Hall.builder().hcode(hcode).tcode(tcode).hname(hname).location(location).build();
        return hall;
    }

    @Builder
    public HallDTO(Long hcode, Long tcode, String hname, String location, LocalDateTime regdate){
        this.hcode = hcode;
        this.tcode = tcode;
        this.hname = hname;
        this.location = location;
        this.regdate = regdate;
    }
}
