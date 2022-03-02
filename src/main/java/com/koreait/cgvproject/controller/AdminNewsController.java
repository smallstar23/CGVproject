package com.koreait.cgvproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminNewsController {

    @GetMapping("manage_news")//manage_news  페이지
    public  String manage_news(){
        return "/admin/news/manage_news";
    }

    @GetMapping("manage_qna")//manage_qna  페이지
    public  String manage_qna(){
        return "/admin/news/manage_qna";
    }
}
