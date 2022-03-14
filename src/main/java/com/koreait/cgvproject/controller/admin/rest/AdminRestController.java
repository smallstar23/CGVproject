package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.dto.HallDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/findTheater/{areacode}")
    public List<TheaterDTO> findTheater(@PathVariable Long areacode) {
        return adminService.findTheaterByAreacode(areacode);
    }

    @GetMapping("/findHall/{tcode}")
    public List<HallDTO> findHall(@PathVariable Long tcode){
        return adminService.findHallByTcode(tcode);
    }
}
