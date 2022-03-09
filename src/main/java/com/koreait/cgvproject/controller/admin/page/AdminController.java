package com.koreait.cgvproject.controller.admin.page;


import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
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


    @GetMapping("admin/login")
    public ModelAndView login() {
        return new ModelAndView("/admin/login");
    }

    @GetMapping("admin/main")
    public ModelAndView main() {
        return new ModelAndView("/admin/main");
    }



//    @PostMapping("admin/main/post")
//    public String postMain(@RequestParam(value = "tcode") String tcode, Model model){
//        session.setAttribute("tcode", tcode);
//        System.out.println("들어온 세션 값 : " + session.getAttribute("tcode"));
//
//         //tcode에 따라 맞춰서 theater 값 불러오기
//        Long newtcode=new Long(Integer.parseInt(tcode));
//         TheaterDTO theater=adminTheaterService.getTheater(newtcode);
//        model.addAttribute("theater",theater);
//        System.out.println("결과 dto: "+theater);
//        model.addAttribute("tcode", session.getAttribute("tcode"));
//        return "admin/main";
//    }
}
