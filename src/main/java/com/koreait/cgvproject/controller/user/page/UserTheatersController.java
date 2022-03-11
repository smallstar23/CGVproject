package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserTheatersController {

    @Autowired
    private AdminTheaterService adminTheaterService;
    @GetMapping("/theaters")
    public String theaters(Model model){
        List<TheaterDTO> theaterDTOList=adminTheaterService.getCGV(1L);
        model.addAttribute("theater",theaterDTOList.get(0));
        model.addAttribute("theaterList",  theaterDTOList);
        return "user/theaters/theaters";
    }

    @GetMapping("/theaters/{tcode}")
    public String theatersview(@PathVariable Long tcode, Model model){
        TheaterDTO theaterDTO=adminTheaterService.getTheater(tcode);
        model.addAttribute("theater", theaterDTO);
        return "/user/theaters/theaters";
    }


    @GetMapping("/theaters/theaterPrice")
    public String thPrice(){
        return "user/theaters/theaterPrice";
    }

}
