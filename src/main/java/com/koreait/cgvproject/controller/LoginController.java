package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/user/login")
    public String login(){
        return "user/login/login";
    }

    @GetMapping("/user/login-agreement")
    public String login_agreement(){
        return "user/login/login-agreement";
    }

    @GetMapping("/user/login-guest-hometicket")
    public String login_guest_hometicket(){
        return "user/login/login-guest-hometicket";
    }

    @GetMapping("/user/login-guest-login")
    public String login_guest_login(){
        return "user/login/login-guest-login";
    }

    @GetMapping("/user/login-guest-reserve")
    public String login_guest_reserve(){
        return "user/login/login-guest-reserve";
    }
}
