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
    private  MovieDTO movie;
    private  Long mcode;
    private  String dnameKo;
    private  String dnameEn;
    private  String dphoto;


    public Director toEntityCreate(){
        return  Director.builder()
                .idx(idx).dnameKo(dnameKo)
                .dnameEn(dnameEn).dphoto(dphoto)
                .movie(movie.toEntity())
                .build();
    }

}

