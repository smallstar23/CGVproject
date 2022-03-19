package com.koreait.cgvproject.controller.admin.page;

import com.koreait.cgvproject.dto.HallDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.service.admin.hall.AdminHallService;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminTheaterController {

    @Autowired
    private AdminTheaterService adminTheaterService;
    @Autowired
    private AdminHallService adminHallService;



    // 극장 리스트
    @GetMapping("/movie/theaters")
    public String movie_theaters(Model model){
        List<TheaterDTO> theaterDTOlist=adminTheaterService.findAll();
        model.addAttribute("theaters",theaterDTOlist);
        return "/admin/theaters/manage_theaters";
    }

    // 상영관 페이지
    @GetMapping("/movie/theaters/{tcode}")
    public String halls(@PathVariable("tcode") Long tcode, Model model){
        List<HallDTO> hallDTOList=adminTheaterService.getHallList(tcode);
        model.addAttribute("halls",hallDTOList);
        model.addAttribute("tcode",tcode);
        return "/admin/theaters/manage_halls";
    }

    //movie-theaters 상영관 등록 페이지
    @GetMapping("/manage/addhall/{tcode}")
    public String movie_theaters_create(@PathVariable("tcode") Long tcode, Model model){
    model.addAttribute("tcode",tcode);
        //TheaterDTO theater=theaterRepository.findByTcode(tcode).toDTO();
        //System.out.println(theater);
    //model.addAttribute("theater",theater);
        return "/admin/theaters/manage_halls_create";
    }

    // 업데이트 페이지에 기존 정보 띄워줌
    @GetMapping("/manage/updatehall/{hcode}")
    public String updateHall(@PathVariable("hcode") Long hcode, Model model){
        model.addAttribute("hcode",hcode);
        HallDTO hallDTO= adminHallService.findHall(hcode);
        model.addAttribute("hall", hallDTO);
        return "/admin/theaters/manage_halls_update";
    }

    @PostMapping("/manage_halls_create")
    public String movie_theaters_write(@ModelAttribute HallDTO hallDTO, Model model){
        Long hallint=hallDTO.getTcode();
        if (adminHallService.savePost(hallDTO) == 1){
            return "redirect:/movie/theaters/"+hallint;
        }
        return "/admin/theaters/manage_halls_create";



    }
}
