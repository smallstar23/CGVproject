package com.koreait.cgvproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminEventController {

    @GetMapping("/event/event-list-special")//event-list-special 페이지
    public  String event_list_special(){
        return  "admin/event/event-list-special";
    }

    @GetMapping("/event/event-result")//event-result 페이지
    public  String event_result(){
        return  "admin/event/event-result";
    }

    @GetMapping("/event/event-done")//event-done 페이지
    public  String event_done(){
        return  "admin/event/event-done";
    }
}
