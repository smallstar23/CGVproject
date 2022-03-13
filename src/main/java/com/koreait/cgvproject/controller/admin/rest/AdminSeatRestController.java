package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.model.dto.SeatDTO;
import com.koreait.cgvproject.service.admin.seat.AdminSeatService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/seat")
public class AdminSeatRestController {


    private AdminSeatService adminSeatService;

    @Transactional
    @PostMapping("/create")
    public boolean create(@RequestBody SeatDTO seatDTO) {
        return adminSeatService.create(seatDTO);

    }

    @Transactional
    @PostMapping("/update")
    public void update(@RequestBody SeatDTO seatDTO) {
        adminSeatService.update(seatDTO);
    }

    @GetMapping("/read/{hcode}")
    public List<SeatDTO> read(@PathVariable Long hcode){
        return adminSeatService.read(hcode);
    }

    @GetMapping("/delete/{hcode}")
    public void delete(@PathVariable Long hcode){
        adminSeatService.delete(hcode);
    }
}

