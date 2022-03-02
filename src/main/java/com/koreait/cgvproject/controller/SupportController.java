package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupportController {

    @GetMapping("/user/support")
    public String support(){
        return "/user/support/support";
    }

    @GetMapping("/user/support-faq-detail-view")
    public String support_faq_detail_view(){
        return "/user/support/support-faq-detail-view";
    }

    @GetMapping("/user/support-faq")
    public String support_faq(){
        return "/user/support/support-faq";
    }

    @GetMapping("/user/support-lease")
    public String support_lease(){
        return "/user/support/support-lease";
    }

    @GetMapping("/user/support-lost")
    public String support_lost(){
        return "/user/support/support-lost";
    }

    @GetMapping("/user/support-news")
    public String support_news(){
        return "/user/support/news";
    }

    @GetMapping("/user/support-news-detail-view")
    public String support_news_detail_view(){
        return "/user/support/support-news-detail-view";
    }

    @GetMapping("/user/support-qna")
    public String support_qna(){
        return "/user/support/support-qna";
    }
}
