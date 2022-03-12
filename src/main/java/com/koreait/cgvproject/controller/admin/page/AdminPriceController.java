package com.koreait.cgvproject.controller.admin.page;

import com.koreait.cgvproject.dto.DefaultDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/admin/price")
public class AdminPriceController {
    private final String ROOT = "/admin/price/";
    @Autowired
    private TheaterRepository theaterRepository;

    @GetMapping("")
    public String price(@RequestParam(value = "tcode",required = false, defaultValue = "none") String tcode, Model model){
        if(tcode.equals("none")){
            DefaultDTO defaultDTO = new DefaultDTO();
            model.addAttribute("theaterDTO", defaultDTO);
            return ROOT + "price";
        }
        Optional<Theater> theaterDTO = theaterRepository.findById(Long.valueOf(tcode));
        theaterDTO.ifPresent(DTO -> model.addAttribute("theaterDTO", DTO));
        return ROOT + "price";
    }

    @GetMapping("/create")//movie-pricem 페이지
    public String priceCreate(@RequestParam Long tcode, Model model){
        Optional<Theater> theaterDTO = theaterRepository.findById(tcode);
        theaterDTO.ifPresent(DTO -> model.addAttribute("theaterDTO", DTO));
        return ROOT + "price_CRUD";
    }
}
