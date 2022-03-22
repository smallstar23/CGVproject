package com.koreait.cgvproject.controller.admin.page;

import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.entity.Schedule;
import com.koreait.cgvproject.repository.MovieRepository;
import com.koreait.cgvproject.service.admin.schedule.AdminScheduleService;
import com.koreait.cgvproject.service.user.moive.UserMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class AdminScheduleController {

    @Autowired
    private AdminScheduleService adminScheduleService;

    @Autowired
    private UserMovieService userMovieService;

    //movie-schedule 페이지
    @GetMapping("movie-schedule")
    public String movie_schedule(Model model){
        List<ScheduleDTO> scheduleDTOlist=adminScheduleService.getScheduleList();
        model.addAttribute("scheduleList",scheduleDTOlist);
        return "/admin/schedule/manage_movieSchedule";
    }

    //movie-schedule 상세페이지
    @GetMapping("movie-schedule/{schecode}")
    public String movie_schedule_detail(@PathVariable("schecode") Long schecode, Model model){
        ScheduleDTO scheduleDTO=adminScheduleService.findSchedule(schecode);
        model.addAttribute("schedule",scheduleDTO);
        return "/admin/schedule/manage_movieScheduledetail";
    }

    // 스케쥴 추가
    @PostMapping("/manage_movieSchedule_create")
    public String schedule_create(@ModelAttribute ScheduleDTO scheduleDTO){
        System.out.println(scheduleDTO);
        adminScheduleService.addSchedule(scheduleDTO);
        return "redirect:movie/schedule";
    }

//    // 스케쥴 수정
//    @PostMapping("/manage_movieSchedule_update")
//    public String schedule_update(@ModelAttribute ScheduleDTO scheduleDTO){
//        System.out.println(scheduleDTO);
//        adminScheduleService.updateSchedule(scheduleDTO);
//        return "redirect:movie/schedule";
//    }

    //movie-schedule 생성페이지
    @GetMapping("/movie/schedule/create")
    public String movie_schedule_create(Model model){
        List<MovieDTO> movieDTOList=userMovieService.getnow("상영중");
        model.addAttribute("movielist", movieDTOList);
        return "/admin/schedule/manage_movieSchedule_create";
    }

    // 삭제
    @GetMapping("/manage_movieSchedule_delete/{schecode}")
    public void schedule_delete(@PathVariable Long schecode){
        adminScheduleService.deleteSchedule(schecode);
    }


}
