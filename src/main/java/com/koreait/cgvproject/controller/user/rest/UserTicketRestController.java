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
    public List<ScheduleDTO> findAllOnlyMcode(@RequestParam Long mcode, @RequestParam String scdate){
        System.out.println("실행은 되니");
        return userScheduleService.findAllOnlyMcode(mcode, scdate);
    }

}
