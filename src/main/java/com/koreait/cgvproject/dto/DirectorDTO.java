package com.koreait.cgvproject.dto;


import com.koreait.cgvproject.entity.Director;
import com.koreait.cgvproject.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorDTO {
    private  Long idx;
    private  Long mcode;
    private  String nameKo;
    private  String nameEn;
    private  String photo;



}