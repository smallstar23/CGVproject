package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/culture-event")
public class UserEventController {
    @GetMapping("/popcorn-store")
    public ModelAndView main(){
        return new ModelAndView("/user/culture-event/popcorn-store/popcorn-store");
    }

    @GetMapping("/popcorn-store/store-category")
    public String category(@RequestParam(value = "cno", required = false, defaultValue = "1") String cno, Model model){
        System.out.println("Get으로 들어온 카테고리 넘버 : " + cno);
        return "user/culture-event/popcorn-store/store-category/store-category";
    }
}
