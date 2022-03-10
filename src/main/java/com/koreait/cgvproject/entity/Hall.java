package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.HallDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "theater")
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "seq_hall",
        sequenceName = "seq_hall",
        initialValue = 1,
        allocationSize = 1
)
public class Hall{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hall")
    private Long hcode;

//    private Long tcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tcode")
    private Theater theater;

    private String hname;
    private String location;

    public HallDTO toDTO(){
        return HallDTO.builder().tcode(theater.getTcode())
                .hcode(hcode).hname(hname).location(location)
                .build();
    }

}
