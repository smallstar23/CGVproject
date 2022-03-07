package com.koreait.cgvproject.controller.admin.page;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @GetMapping("admin/login")
    public ModelAndView login() {
        return new ModelAndView("/admin/login");
    }
}
