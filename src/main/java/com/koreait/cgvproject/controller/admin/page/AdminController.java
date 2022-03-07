package com.koreait.cgvproject.controller.admin.page;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController{

    @Autowired
    private HttpSession session;

    @GetMapping("admin/login")
    public ModelAndView login() {
        return new ModelAndView("/admin/login");
    }
    @PostMapping("admin/main/post")
    public String postMain(@RequestParam(value = "tcode") String tcode, Model model){
        session.setAttribute("tcode", tcode);
        System.out.println("들어온 세션 값 : " + session.getAttribute("tcode"));
        model.addAttribute("tcode", session.getAttribute("tcode"));
        return "admin/main";
    }
}
