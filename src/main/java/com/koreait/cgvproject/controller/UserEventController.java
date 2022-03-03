package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/culture-event")
public class UserEventController {
    @GetMapping("/popcorn-store")
    public ModelAndView main(){
        return new ModelAndView("/user/culture-event/popcorn-store");
    }

    @GetMapping("/popcorn-store/store-category")
    public String category(@RequestParam(value = "cno", required = false, defaultValue = "1") String cno, Model model){
        System.out.println("Get으로 들어온 카테고리 넘버 : " + cno);
        return "user/culture-event/popcorn-store/store-category";
    }

    @GetMapping("/popcorn-store/product-detail")
    public String detail(){
        return "user/culture-event/popcorn-store/product-detail";
    }

    @GetMapping("/popcorn-store/user-cart")
    public String userCart(){
        return "user/culture-event/popcorn-store/user-cart";
    }

    @GetMapping("/popcorn-store/user-gift")
    public String userGift(){
        return "user/culture-event/popcorn-store/user-gift";
    }
}
