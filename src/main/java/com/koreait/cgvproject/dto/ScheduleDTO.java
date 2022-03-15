package com.koreait.cgvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDTO {

    private Long schecode;
    private MovieDTO movieDTO;
    private Long mcode;
    private HallDTO hallDTO;
    private Long hcode;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime scdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime enddate;

}
