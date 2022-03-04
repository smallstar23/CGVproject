package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.service.Kakaopay;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Log
@Controller
public class UserTicketController {


    @GetMapping("ticket")
    public String reservation(){
        return "user/ticket/reservation";
    }

    @GetMapping("/user/ticket/home_ticket")
    public String homeTicket(){
        return "user/ticket/home_ticket";
    }
    @GetMapping("paymentDone")
    public String paymentDone(){
        return "user/ticket/paymentDone";
    }



    @Setter(onMethod_ = @Autowired)
    private Kakaopay kakaopay;


    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay() {
        log.info("kakaoPay post............................................");

        return "redirect:" + kakaopay.kakaoPayReady();

    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));

    }




}
