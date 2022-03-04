package com.koreait.cgvproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminNewsController {

    @GetMapping("manage_news")//manage_news  페이지
    public  String manage_news(){
        return "/admin/news/manage_news";
    }

    @GetMapping("manage_news_detail")//manage_news  페이지
    public  String manage_news_detail(){
        return "/admin/news/manage_news_detail";
    }

    @GetMapping("manage_news_write")//manage_news  페이지
    public  String manage_news_write(){
        return "/admin/news/manage_news_write";
    }




    @GetMapping("manage_qna")//manage_qna  페이지
    public  String manage_qna(){
        return "/admin/news/manage_qna";
    }

    @GetMapping("manage_qna_detail")//manage_qna  페이지
    public  String manage_qna_detail(){
        return "/admin/news/manage_qna_detail";
    }

    @GetMapping("manage_qna_write")//manage_qna  페이지
    public  String manage_qna_write(){
        return "/admin/news/manage_qna_write";
    }
}
