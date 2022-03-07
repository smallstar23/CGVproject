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

@Controller
public class AdminTheaterRestController {

    @Autowired
    private AdminTheaterService adminTheaterService;

    @PostMapping("/selTheater")
    @ResponseBody
    private TheaterDTO getTheater(@RequestParam("tname") String tname){
        System.out.println("전달 받은 theater name:" + tname);
        TheaterDTO findTheater= adminTheaterService.getTheater(tname).toDTO();
        System.out.println(findTheater);
        return findTheater;
    }



}
