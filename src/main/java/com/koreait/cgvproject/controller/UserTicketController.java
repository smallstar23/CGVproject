package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserTicketController {


    @GetMapping("ticket")
    public String ticket(){

        return "user/ticket/ticket";
    }


    @GetMapping("ticket/reservation")
    public String reservation(){
        return "user/ticket/reservation";
    }

}
