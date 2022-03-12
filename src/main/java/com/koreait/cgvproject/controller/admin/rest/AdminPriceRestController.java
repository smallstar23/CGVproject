package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.dto.PriceDTO;
import com.koreait.cgvproject.service.admin.price.AdminPriceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/price")
public class AdminPriceRestController {

    private AdminPriceService adminPriceService;

    @PostMapping("/create")
    public void create(@RequestBody PriceDTO priceDTO){
        System.out.println(priceDTO);
        adminPriceService.create(priceDTO);
    }
}
