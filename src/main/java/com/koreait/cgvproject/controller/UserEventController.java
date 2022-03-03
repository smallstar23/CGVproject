package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/culture-event")
public class UserEventController {

    @GetMapping("/defaultNew")
    public ModelAndView main(){
        return new ModelAndView("/user/culture-event/defaultNew");
    }

    @GetMapping("/event/event-category")
    public String category(@RequestParam(value = "cno", required = false, defaultValue = "1") String cno, Model model){
        System.out.println("Get으로 들어온 카테고리 넘버 : " + cno);
        return "user/culture-event/event/event-category";
    }

}
