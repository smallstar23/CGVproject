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

    public Gift toEntity() {
        return Gift.builder().gcode(gcode).category(category).title(title).gname(gname).content(content).price(price)
                .gfile(gfile).endMonth(endMonth).build();
    }
}
