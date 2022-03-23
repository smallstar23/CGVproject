package com.koreait.cgvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDTO {

    private Long ticode;
    private ScheduleDTO scheduleDTO;
    private Long schecode;
    private String seat;
    private String movieName;
    private MemberDTO memberDTO;
    private Long memIdx;
    private Long adultnum;
    private Long youthnum;
    private String price;
    private LocalDateTime paydate;
    private LocalDateTime candate;
    private String usepoint;
    private String totprice;

}
