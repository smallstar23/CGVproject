package com.koreait.cgvproject.model.entity;

import com.koreait.cgvproject.model.dto.SeathtmlDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name = "seq_seathtml",
        sequenceName = "seq_seathtml",
        initialValue = 1,
        allocationSize = 1
)
@Builder
public class Seathtml {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_seathtml")
    private Long stIdx;

    private String stRow;
    private String stCol;
    private String rowEmpty;
    private String colEmpty;

    @OneToOne
    @JoinColumn(name = "hcode")
    private Hall hall;

    public SeathtmlDTO toDTO(){
        return SeathtmlDTO.builder()
                .stIdx(stIdx)
                .stRow(stRow)
                .stCol(stCol)
                .rowEmpty(rowEmpty)
                .colEmpty(colEmpty)
                .hcode(hall.getHcode())
                .build();
    }
}
