package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.SeathtmlDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Long hcode;
    private String stRow;
    private String stCol;
    private String rowEmpty;
    private String colEmpty;

    public SeathtmlDTO toDTO(){
        return SeathtmlDTO.builder()
                .stIdx(stIdx).hcode(hcode).stRow(stRow).stCol(stCol).rowEmpty(rowEmpty)
                .colEmpty(colEmpty).build();
    }
}
