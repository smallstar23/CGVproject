package com.koreait.cgvproject.controller.admin.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/seat")
public class AdminSeatController {

    @GetMapping("")
    public String seat(){
        return "admin/seat/seat";
    }
}
