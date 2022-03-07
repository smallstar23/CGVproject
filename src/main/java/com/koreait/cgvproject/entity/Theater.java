package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.TheaterDTO;
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
        name = "seq_theater",
        sequenceName = "seq_theater",
        initialValue = 1,
        allocationSize = 1
)
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_theater")
    private Long tcode;
    private String tname;
    private String location;
    private String hp;

    public TheaterDTO toDTO(){
        return TheaterDTO.builder().tcode(tcode).tname(tname).location(location).hp(hp).build();
    }
}
