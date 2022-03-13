package com.koreait.cgvproject.dto;

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
    private Long mem_idx;
    private String kind;
    private Long tcode;
    private Long valPoint;
    private Long pointChange;
    private LocalDateTime regDate;
}
