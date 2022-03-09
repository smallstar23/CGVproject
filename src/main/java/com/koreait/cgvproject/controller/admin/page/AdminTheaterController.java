package com.koreait.cgvproject.controller.admin.page;

import com.koreait.cgvproject.dto.HallDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.TheaterRepository;
import com.koreait.cgvproject.service.admin.hall.AdminHallService;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminTheaterController {

    @Autowired
    private AdminTheaterService adminTheaterService;
    @Autowired
    private AdminHallService adminHallService;

    @Autowired
    private TheaterRepository theaterRepository;

    // 극장 리스트
    @GetMapping("/movie/theaters")
    public String movie_theaters(Model model){
        List<TheaterDTO> theaterDTOlist=adminTheaterService.findall();
        model.addAttribute("theaters",theaterDTOlist);
        return "/admin/movie/manage_theaters";
    }

    // 상영관 페이지
    @GetMapping("/movie/theaters/{tcode}")
    public String halls(@PathVariable("tcode") Long tcode, Model model){
        System.out.println(tcode);
        List<HallDTO> hallDTOList=adminTheaterService.getHallList(tcode);
        System.out.println(hallDTOList);
        model.addAttribute("halls",hallDTOList);
        return "/admin/movie/manage_halls";
    }

    //movie-theaters 상영관 등록 페이지
    @GetMapping("/manage_theaters_create")
    public String movie_theaters_create(){
        return "/admin/movie/manage_theaters_create";
    }

    @PostMapping("/manage_theaters_create")
    public String movie_theaters_write(@ModelAttribute HallDTO hallDTO, Model model){
        System.out.println(hallDTO);
        adminHallService.savePost(hallDTO);

        //List<HallDTO> hallDTOList = adminHallService.getHallList();
       //model.addAttribute("HallList",hallDTOList);


        return "redirect:/manage_theaters";
    }
}
