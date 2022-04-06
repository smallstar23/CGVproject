package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.entity.Schedule;
import com.koreait.cgvproject.service.admin.schedule.AdminScheduleService;
import com.koreait.cgvproject.service.user.moive.UserMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminScheduleRestController {

    @Autowired
    private AdminScheduleService adminScheduleService;

    @Autowired
    private UserMovieService userMovieService;

    // 스케쥴 추가
    @PostMapping("/manage_movieSchedule_create")
    public String schedule_create(@ModelAttribute ScheduleDTO scheduleDTO){
        System.out.println(scheduleDTO);
        adminScheduleService.addSchedule(scheduleDTO);
        return "redirect:movie-schedule";
    }

    // hcode에 따라 schedule이 있는지 확인할 것
    @PostMapping("/findSchedule")
    @ResponseBody
    public List<ScheduleDTO> schedule_find(@RequestParam("hcode") Long hcode){
        System.out.println(hcode);
        List<ScheduleDTO> scheduleDTOList=adminScheduleService.hallSchedule(hcode);
        System.out.println(scheduleDTOList);
        return scheduleDTOList;
    }


}
