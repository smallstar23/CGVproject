package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointDTO {

    private Long idx;
    private Long pcode1;
    private Long pcode2;
    private Long pcode3;
    private MemberDTO memberDTO;
    private Long mem_idx;
    private String kind;
    private TheaterDTO theaterDTO;
    private Long tcode;
    private Long valPoint;
    private Long pointChange;
    private LocalDateTime regDate;
}
