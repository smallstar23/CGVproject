package com.koreait.cgvproject.model.dto;

import com.koreait.cgvproject.model.entity.Hall;
import com.koreait.cgvproject.model.entity.Seathtml;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeathtmlDTO {
    private Long stIdx;
    private Long hcode;
    private String stRow;
    private String stCol;
    private String rowEmpty;
    private String colEmpty;

    public Seathtml toEntityForCreate(Hall hall){
        return Seathtml.builder().stRow(stRow).stCol(stCol).rowEmpty(rowEmpty)
                .colEmpty(colEmpty).hall(hall).build();
    }
}
