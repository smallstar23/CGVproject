package com.koreait.cgvproject.controller.user.rest;

import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserTheaterRestController {

    @Autowired
    private AdminTheaterService adminTheaterService;

    @PostMapping("/theatersList")
    public List<TheaterDTO> theaterDTOList(@RequestParam("areacode") Long areacode){
        List<TheaterDTO> theaterDTOList=adminTheaterService.getCGV(areacode);
        return theaterDTOList;
    }
}
