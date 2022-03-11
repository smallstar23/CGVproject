package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.dto.SeatDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Seat;
import com.koreait.cgvproject.repository.HallRepository;
import com.koreait.cgvproject.repository.SeatRepository;
import com.koreait.cgvproject.service.admin.seat.AdminSeatService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

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

