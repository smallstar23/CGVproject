package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.TheaterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "seq_theater",
        sequenceName = "seq_theater",
        initialValue = 1,
        allocationSize = 1
)
@Entity
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_theater")
    private Long tcode;
    private String tname;
    private Long areacode;
    private String location;
    private String hp;
    private String photo;

    public TheaterDTO toDTO(){
        return TheaterDTO.builder().tcode(tcode).tname(tname).areacode(areacode).location(location).hp(hp).photo(photo).build();
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "theater")
    private Price price;
}
