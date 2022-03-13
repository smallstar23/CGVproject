package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Trailer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrailerDTO {
    private Long idx;
    private MovieDTO movieDTO;
    private Long mcode;
    private String description1;
    private String description2;
    private String description3;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String photo5;
    private String trailer1;
    private String trailer2;
    private String trailer3;

    public Trailer toEntityForCreate(){
        return  Trailer.builder()
                .idx(idx).description1(description1)
                .description2(description2).description3(description3)
                .photo1(photo1).photo2(photo2).photo3(photo3).photo4(photo4)
                .photo5(photo5)
                .trailer1(trailer1).trailer2(trailer2).trailer3(trailer3)
                .movie(movieDTO.toEntity())
                .build();

    }
}
