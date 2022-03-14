package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.dto.HallDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.service.admin.hall.AdminHallService;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminTheaterRestController {

    @Autowired
    private AdminTheaterService adminTheaterService;

    @Autowired
    private AdminHallService adminHallService;

    @PostMapping("/api/areacode")
    @ResponseBody
    public List<TheaterDTO> areacode(@RequestParam("acode") int acode, Model model) {
        Long area = Long.valueOf(String.valueOf(acode));
        List<TheaterDTO> cgvlist = adminTheaterService.getCGV(area);
        return cgvlist;
    }

    @PostMapping("/api/tcode")
    @ResponseBody
    public List<HallDTO> theaterHall(@RequestParam("tcode") Long tcode, Model model) {
        System.out.println(tcode);
        List<HallDTO> hallDTOList=adminTheaterService.getHallList(tcode);
        return hallDTOList;
    }

}
