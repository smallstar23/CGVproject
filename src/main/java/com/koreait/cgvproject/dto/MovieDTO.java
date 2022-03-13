package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO {

    private Long mcode;
    private String mscreen;
    private String titleKo;
    private String titleEn;
    private String genre;
    private String country;
    private String movieRating;
    private Long runtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate launchDate;
    private LocalDateTime regDate;
    private String poster;



// [2022-03-01]
    public Movie toEntity(){
        return Movie.builder()
                .mcode(mcode).mscreen(mscreen).titleKo(titleKo).titleEn(titleEn).genre(genre).country(country)
                .movieRating(movieRating).runtime(runtime).launchDate(launchDate)
                .regDate(LocalDateTime.now()).poster(poster).build();
    }

}
