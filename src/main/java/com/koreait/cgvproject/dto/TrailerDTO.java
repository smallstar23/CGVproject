package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrailerDTO {
    private Long idx;
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
}
