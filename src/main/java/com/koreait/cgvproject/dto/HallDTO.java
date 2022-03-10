package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.TheaterRepository;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HallDTO{

    private Long hcode;
    private TheaterDTO theater;
    private String hname;
    private String location;

    public Hall toEntity() {
        return Hall.builder()
                .hcode(hcode)
                .theater(theater.toEntity())
                .hname(hname)
                .location(location)
                .build();
    }
}
