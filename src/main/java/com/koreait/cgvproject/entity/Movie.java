package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.GiftDTO;
import com.koreait.cgvproject.dto.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "seq_movie",
        sequenceName="seq_movie",
        initialValue = 1,
        allocationSize = 1
)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_movie")
    private Long mcode;
    private String titleKo;
    private String titleEn;
    private String genre;
    private String country;
    private String movieRating;
    private Long runtime;
    private LocalDateTime launchDate;
    private LocalDateTime regDate;
    private String poster;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<Actor> actors;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<Director> directors;


    public MovieDTO toDTO(){
        return MovieDTO.builder().mcode(mcode).titleKo(titleKo).titleEn(titleEn).genre(genre).country(country)
                .movieRating(movieRating).runtime(runtime).launchDate(launchDate).regDate(regDate).poster(poster).build();
    }
}
