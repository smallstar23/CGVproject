package com.koreait.cgvproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {


    @RequestMapping("/main")// http://localhost:8080/main
    public ModelAndView main(){
        return new ModelAndView("/admin/main");
    }

}
