package com.koreait.cgvproject.controller.user.rest;

import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.service.admin.schedule.AdminScheduleService;
import com.koreait.cgvproject.service.user.schedule.UserScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserTicketRestController {

    @Autowired
    UserScheduleService userscheduleService;


    @PostMapping("/api/findSchedule")
    public List<ScheduleDTO> scheduleDTOList(@RequestBody ScheduleDTO scheduleDTO){
        List<ScheduleDTO> scheduleDTOlist=userscheduleService.findScheduleList(scheduleDTO.getMcode(), scheduleDTO.getTcode());
        System.out.println(scheduleDTOlist);
        return scheduleDTOlist;
    }


}
