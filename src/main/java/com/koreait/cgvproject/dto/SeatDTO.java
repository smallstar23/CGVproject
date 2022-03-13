package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatDTO {
    private Long stcode;
    private String stNum;
    private Integer disabled;

    // private Hall hall에 해당하는 부분
    private Long hcode;

    public Seat toEntityForCreateHallRequired(Hall hall){
        return Seat.builder()
                .stcode(stcode)
                .stNum(stNum)
                .disabled(disabled)
                .hall(hall).build();
    }
}
