package com.koreait.cgvproject.controller.admin.page.rest;

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
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminTheaterRestController {

    @Autowired
    private AdminTheaterService adminTheaterService;

    @PostMapping("/areacode")
    @ResponseBody
    public List<TheaterDTO> areacode(@RequestParam("acode") int acode, Model model){
        Long area=new Long(acode);
        List<TheaterDTO> cgvlist=adminTheaterService.getCGV(area);
        System.out.println(cgvlist);
        model.addAttribute("cgvlist",cgvlist);
        return cgvlist;
    }




}
