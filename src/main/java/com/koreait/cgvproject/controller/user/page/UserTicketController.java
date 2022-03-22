package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.service.Kakaopay;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import com.koreait.cgvproject.service.user.moive.UserMovieService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Log
@Controller
@AllArgsConstructor
public class UserTicketController {

    private UserMovieService userMovieService;
    private AdminTheaterService adminTheaterService;
    private HttpSession session;

    @GetMapping("ticket")
    public String reservation(Model model){
        List<MovieDTO> movieDTOList=userMovieService.getList();
        List<TheaterDTO> theaterDTOList=adminTheaterService.findAll();
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
    public String kakaoPay(@RequestParam String movieName,
                           @RequestParam Long memIdx,
                           @RequestParam String selSeat,
                           @RequestParam Long schecode
                           ) {
        System.out.println(movieName);
        System.out.println(memIdx);
        System.out.println(selSeat);
        System.out.println(schecode);
        // 세션값 만들어서 여기서 세션으로 추가해도되는데 다른 방식으로 해도됨

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
