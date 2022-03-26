package com.koreait.cgvproject.controller.user.rest;

import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.repository.ScheduleRepository;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import com.koreait.cgvproject.service.user.schedule.UserScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class UserTheaterRestController {

    @Autowired
    private AdminTheaterService adminTheaterService;

    @Autowired
    private UserScheduleService userScheduleService;

    @PostMapping("/theatersList")
    public List<TheaterDTO> theaterDTOList(@RequestParam("areacode") Long areacode){
        List<TheaterDTO> theaterDTOList=adminTheaterService.getCGV(areacode);
        return theaterDTOList;
    }

    @PostMapping("/Schedule")
    public List<ScheduleDTO> scheduleDTOList(@RequestParam("tcode") Long tcode,
                                             @RequestParam("newschdate") String schedate){

        LocalDateTime newschedate=LocalDateTime.parse(schedate+"T00:00:00", DateTimeFormatter.ISO_DATE_TIME);
        List<ScheduleDTO> scheduleDTOList=userScheduleService.findbyDate(tcode, newschedate);
        return scheduleDTOList;
    }

    @PostMapping("/TheaterName")
    public TheaterDTO theaterDTO(@RequestParam("tname") String tname){
        TheaterDTO theaterDTO=adminTheaterService.getTnameSendtheater(tname);
        return theaterDTO;
    }
}
