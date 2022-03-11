package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.dto.SeathtmlDTO;
import com.koreait.cgvproject.service.admin.seat.AdminSeatHtmlService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seatHtml")
@AllArgsConstructor
public class AdminSeatHtmlRestController {

    private AdminSeatHtmlService adminSeatHtmlService;

    @GetMapping("/exist/{hcode}")
    public boolean seatHtmlExist(@PathVariable Long hcode){
        return adminSeatHtmlService.seatHtmlExist(hcode);
    }

    @PostMapping("/create")
    public void seatHtmlCreate(@RequestBody SeathtmlDTO seathtmlDTO){
        adminSeatHtmlService.seatHtmlCreate(seathtmlDTO);
    }
    @GetMapping("/read/{hcode}")
    public SeathtmlDTO seatHtmlRead(@PathVariable Long hcode){
        return adminSeatHtmlService.seatHtmlRead(hcode);
    }
    @PostMapping("/update")
    public void seatHtmlUpdate(@RequestBody SeathtmlDTO seatHtmlDTO){
        adminSeatHtmlService.seatHtmlUpdate(seatHtmlDTO);
    }
    @GetMapping("/delete/{stIdx}")
    public void seatHtmlDelete(@PathVariable Long stIdx){
        adminSeatHtmlService.seatHtmlDelete(stIdx);
    }
}
