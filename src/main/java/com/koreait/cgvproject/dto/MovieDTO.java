package com.koreait.cgvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO {

    private Long idx;
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




}
