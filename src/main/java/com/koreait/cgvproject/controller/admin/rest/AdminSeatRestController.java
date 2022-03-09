package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.repository.SeatHtmlRepository;
import com.koreait.cgvproject.service.admin.AdminSeatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api")
@AllArgsConstructor
public class AdminSeatRestController {

    private AdminSeatService adminSeatService;

    @GetMapping("/exist/{hcode}")
    public Integer seatHtmlExist(@PathVariable Long hcode){
        return adminSeatService.seatHtmlExist(hcode);
    }
}
