package com.koreait.cgvproject.model.entity;

import com.koreait.cgvproject.model.dto.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;
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
@Table(name="movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_movie")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<Actor> actors;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<Director> director;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<Trailer> trailer;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
//    private List<Reply> replies;


    public MovieDTO toDTO(){
        return MovieDTO.builder().mcode(mcode).mscreen(mscreen).titleKo(titleKo).titleEn(titleEn).genre(genre).country(country)
                .movieRating(movieRating).runtime(runtime).launchDate(launchDate).regDate(LocalDateTime.now()).poster(poster).build();
    }


}
