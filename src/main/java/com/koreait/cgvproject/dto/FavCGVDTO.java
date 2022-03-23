package com.koreait.cgvproject.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FavCGVDTO {

    private Long idx;
    private Long memIdx;
    private String tname;
    private Long areacode;
}
