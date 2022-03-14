package com.koreait.cgvproject.controller.admin.page;

import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.service.admin.schedule.AdminScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class AdminScheduleController {

    @Autowired
    private AdminScheduleService adminScheduleService;


    @GetMapping("manage_movieSchedule")//movie-schedule 페이지
    public String movie_schedule(Model model){
        List<ScheduleDTO> scheduleDTOlist=adminScheduleService.getScheduleList();
        model.addAttribute("scheduleList",scheduleDTOlist);
        return "/admin/movie/manage_movieSchedule";
    }

    @GetMapping("/manage_movieSchedule_create")//movie-schedule 페이지
    public String movie_schedule_create(){
        return "/admin/movie/manage_movieSchedule_create";
    }


}
