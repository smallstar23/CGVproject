package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/culture-event")
public class UserStoreController {

    @GetMapping("/popcorn-store")
    public ModelAndView main(){
        return new ModelAndView("/user/culture-event/popcorn-store");
    }

    @GetMapping("/popcorn-store/store-category")
    public String category(@RequestParam(value = "cno", required = false, defaultValue = "1") String cno, Model model){
        if(cno.equals("1")) System.out.println("default 카테고리 넘버 : " + cno);
        else System.out.println("Get으로 들어온 카테고리 넘버 : " + cno);
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

    @GetMapping("/popcorn-store/store-payment")
    public String giftPay(){
        return "user/culture-event/popcorn-store/store-payment";
    }

    @GetMapping("/popcorn-store/purchase-confirm")
    public String directPay(){
        return "user/culture-event/popcorn-store/purchase-confirm";
    }

    @GetMapping("/popcorn-store/payment-successcomplete")
    public String success(){
        return "user/culture-event/popcorn-store/payment-successcomplete";
    }

    @GetMapping("/popcorn-store/payment-failcomplete")
    public String fail(){
        return "user/culture-event/popcorn-store/payment-failcomplete";
    }
}