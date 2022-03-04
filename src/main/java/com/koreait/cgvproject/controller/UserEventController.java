package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/culture-event")
public class UserEventController {

    @GetMapping("/defaultNew")
    public String defaultNew(){return "/user/culture-event/defaultNew";}

}
