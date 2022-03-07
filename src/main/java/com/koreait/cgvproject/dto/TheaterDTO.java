package com.koreait.cgvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterDTO {

    private Long tcode;
    private String tname;
    private Long areacode;
    private String location;
    private String hp;
    private String photo;
}
