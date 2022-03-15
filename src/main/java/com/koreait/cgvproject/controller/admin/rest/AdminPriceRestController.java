package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.dto.PriceDTO;
import com.koreait.cgvproject.service.admin.price.AdminPriceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/price")
public class AdminPriceRestController {

    private AdminPriceService adminPriceService;

    @PostMapping("/create")
    public void create(@RequestBody PriceDTO priceDTO){
        adminPriceService.create(priceDTO);
    }

    @GetMapping("/read")
    public List<PriceDTO> read(@RequestParam Long tcode){
        return adminPriceService.read(tcode);
    }

    @GetMapping("/exist")
    public boolean isExist(@RequestParam(value = "tcode", required = false, defaultValue = "0") String tcode){
        //string 으로 받았기 때문에 변환해줍니다.
        return adminPriceService.isExist(Long.parseLong(tcode));
    }

    @PostMapping("/update")
    public void update(@RequestBody PriceDTO priceDTO){
        adminPriceService.update(priceDTO);
    }
    @GetMapping("/delete/{tcode}")
    public void delete(@PathVariable Long tcode){
        adminPriceService.delete(tcode);
    }
}
