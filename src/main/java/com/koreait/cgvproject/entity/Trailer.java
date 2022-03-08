package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.dto.TrailerDTO;
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




    public TrailerDTO toDTO(){
        return TrailerDTO.builder().idx(idx).mcode(mcode).description1(description1).description2(description2)
                .description3(description3).photo1(photo1).photo2(photo2).photo3(photo3).photo4(photo4)
                .photo5(photo5).trailer1(trailer1).trailer2(trailer2).trailer3(trailer3).build();
    }

}
