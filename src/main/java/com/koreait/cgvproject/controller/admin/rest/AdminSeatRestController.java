package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.dto.SeathtmlDTO;
import com.koreait.cgvproject.entity.Seathtml;
import com.koreait.cgvproject.service.admin.seat.AdminSeatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seatHtml")
@AllArgsConstructor
public class AdminSeatRestController {

    private AdminSeatService adminSeatService;

    @GetMapping("/exist/{hcode}")
    public boolean seatHtmlExist(@PathVariable Long hcode){
        return adminSeatService.seatHtmlExist(hcode);
    }

    @PostMapping("/create")
    public void seatHtmlCreate(@RequestBody SeathtmlDTO seathtmlDTO){
        adminSeatService.seatHtmlCreate(seathtmlDTO);
    }
    @GetMapping("/read/{hcode}")
    public SeathtmlDTO seatHtmlRead(@PathVariable Long hcode){
        return adminSeatService.seatHtmlRead(hcode);
    }
    @PostMapping("/update")
    public void seatHtmlUpdate(@RequestBody SeathtmlDTO seatHtmlDTO){
        adminSeatService.seatHtmlUpdate(seatHtmlDTO);
    }
    @GetMapping("/delete/{stIdx}")
    public void seatHtmlDelete(@PathVariable Long stIdx){
        adminSeatService.seatHtmlDelete(stIdx);
    }
}
