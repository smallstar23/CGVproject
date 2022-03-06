package com.koreait.cgvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO {

    private int idx;
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


}
