package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.HallDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private Long hguan;
    private String hname;
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tcode")
    private Theater theater;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "hall")
    private Seathtml seathtml;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
    private List<Seat> seatList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
    private List<Schedule> scheduleList;

    public HallDTO toDTO(){
        return HallDTO.builder()
                .theater(theater.toDTO())
                .hguan(hguan)
                .seatSize(seatList.size())
                .hcode(hcode).hname(hname).location(location)
                .build();
    }

}
