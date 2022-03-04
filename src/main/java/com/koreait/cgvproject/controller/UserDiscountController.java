package com.koreait.cgvproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserDiscountController {

    @GetMapping("discount/discount")
    public String discount(){
        return  "/user/discount/discount";
    }
    @GetMapping("discount/discountlist")
    public String category(@RequestParam(value = "cno", required = false, defaultValue = "1") String cno, Model model){
        System.out.println("Get으로 들어온 카테고리 넘버 : " + cno);
        return "user/discount/discountlist";
    }

    @GetMapping("discount/ClubService")
    public String clubservice(){
        return  "/user/discount/ClubService";
    }

}
