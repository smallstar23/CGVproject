package com.koreait.cgvproject.controller.user.rest;

import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.service.user.schedule.UserScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserTicketRestController {

    @Autowired
    UserScheduleService userScheduleService;


    @PostMapping("/api/findSchedule")
    public List<ScheduleDTO> scheduleDTOList(@RequestBody ScheduleDTO scheduleDTO){
        return userScheduleService.findScheduleList(scheduleDTO.getMcode(), scheduleDTO.getTcode());
    }
    @GetMapping("/api/findSchedule")
    public List<ScheduleDTO> findAllOnlyMcode(@RequestParam Long mcode, @RequestParam String scdate,
                                              @RequestParam Long tcode){
        return userScheduleService.findAllOnlyMcode(mcode, tcode, scdate);
    }

    @GetMapping("/api/schedule/getSeatCount")
    public Long getSeatCount(@RequestParam Long hcode){
        return userScheduleService.getSeatCount(hcode);
    }

}
