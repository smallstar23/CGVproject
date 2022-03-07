package com.koreait.cgvproject.controller.user.rest;

import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminTheaterRestController {

    @Autowired
    private AdminTheaterService adminTheaterService;

    @PostMapping("/login")
    private String login(@RequestParam("tname") String tname, HttpServletRequest request){
        System.out.println("전달 받은 theater name:" + tname);
        HttpSession session= request.getSession();
        session.setAttribute("tname", tname);
        return "ok";
    }



}
