package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.GiftDTO;
import com.koreait.cgvproject.dto.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    private int idx;
    @Id
    private Long mcode;
    private String titleKo;
    private String titleEn;
    private String genre;
    private String country;
    private String movieRating;
    private int runtime;
    private LocalDateTime launchDate;
    private LocalDateTime regDate;
    private String poster;

    public MovieDTO toDTO(){
        return MovieDTO.builder().mcode(mcode).titleKo(titleKo).titleEn(titleEn).genre(genre).country(country)
                .movieRating(movieRating).runtime(runtime).launchDate(launchDate).regDate(regDate).poster(poster).build();
    }
}
