package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("user-main")
    public ModelAndView main(){
        return new ModelAndView("/user/main");
    }

    @GetMapping("discount")
    public String discount(){
        return "user/discount";
    }

    @GetMapping("ClubService")
    public String clubservice(){
        return "user/ClubService";
    }


}
