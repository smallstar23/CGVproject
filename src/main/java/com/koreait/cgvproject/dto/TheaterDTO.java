package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Theater;
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


    public Theater toEntity(){
        return Theater.builder().tcode(tcode).tname(tname).areacode(areacode)
                .location(location).hp(hp).photo(photo).build();
    }

}
