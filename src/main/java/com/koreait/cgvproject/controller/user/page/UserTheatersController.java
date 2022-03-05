package com.koreait.cgvproject.controller.user.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserTheatersController {

    @GetMapping("/theaters")
    public String theaters(){
        return "user/theaters/theaters";
    }


    @GetMapping("/theaters/theaterPrice")
    public String thPrice(){
        return "user/theaters/theaterPrice";
    }

}
