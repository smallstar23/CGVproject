package com.koreait.cgvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDTO {

    private Long ticode;
    private ScheduleDTO scheduleDTO;
    private Long schecode;
    private SeatDTO seatDTO;
    private Long stcode;
    private MemberDTO memberDTO;
    private Long memIdx;
}
