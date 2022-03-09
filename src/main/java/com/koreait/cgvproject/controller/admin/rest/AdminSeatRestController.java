package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.dto.SeathtmlDTO;
import com.koreait.cgvproject.entity.Seathtml;
import com.koreait.cgvproject.service.admin.seat.AdminSeatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AdminSeatRestController {

    private AdminSeatService adminSeatService;

    @GetMapping("/seatHtml/exist/{hcode}")
    public boolean seatHtmlExist(@PathVariable Long hcode){
        return adminSeatService.seatHtmlExist(hcode);
    }

    @PostMapping("/seatHtml/create")
    public void seatHtmlCreate(@RequestBody SeathtmlDTO seathtmlDTO){
        adminSeatService.seatHtmlCreate(seathtmlDTO);
    }
}
