package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.service.Kakaopay;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import com.koreait.cgvproject.service.user.moive.UserMovieService;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Log
@Controller
public class UserTicketController {

    @Autowired
    private UserMovieService userMovieService;

    @Autowired
    private AdminTheaterService adminTheaterService;

    @GetMapping("ticket")
    public String reservation(Model model){
        List<MovieDTO> movieDTOList=userMovieService.getList();
        List<TheaterDTO> theaterDTOList=adminTheaterService.findall();
        model.addAttribute("movielist", movieDTOList);
        model.addAttribute("theaterlist",theaterDTOList);
        return "user/ticket/reservation";
    }



    @GetMapping("/user/ticket/home_ticket")
    public String homeTicket(){
        return "user/ticket/home_ticket";
    }


    @Setter(onMethod_ = @Autowired)
    private Kakaopay kakaopay;


    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestParam String movieName) {
        log.info("kakaoPay post............................................");
        return "redirect:" + kakaopay.kakaoPayReady(movieName);

    }

    @GetMapping("ticket/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
        return "user/ticket/kakaoPaySuccess";

    }



}
