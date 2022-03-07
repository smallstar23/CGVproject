package com.koreait.cgvproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "seq_trailer",
        sequenceName="seq_trailer",
        initialValue = 1,
        allocationSize = 1
)
public class Trailer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_trailer")
    private Long idx;

    // private Long mcode;
    @OneToOne
    private Movie movie;

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
