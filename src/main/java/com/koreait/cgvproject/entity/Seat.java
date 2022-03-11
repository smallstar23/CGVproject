package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.SeatDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name = "seq_seat",
        sequenceName = "seq_seat",
        initialValue = 1,
        allocationSize = 1
)
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_seat")
    private Long stcode;
    private String stNum;
    private Integer disabled;

    @ManyToOne
    @JoinColumn(name = "hcode")
    private Hall hall;

    public SeatDTO toDTO(){
        return SeatDTO.builder()
                .stcode(stcode)
                .stNum(stNum)
                .disabled(disabled)
                .hcode(hall.getHcode())
                .build();
    }
}
