package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Gift;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftDTO{
    private Long gcode;
    private String category;
    private String title;
    private String gname;
    private String content;
    private String price;
    private String gfile;
    private String endMonth;
    private Integer mainon;
}
