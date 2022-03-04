package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserSupportController {

    @GetMapping("/support")
    public String support(){
        return "user/support/support";
    }

    @GetMapping("/support/faq/support-faq-detail-view")
    public String support_faq_detail_view(){
        return "user/support/faq/support-faq-detail-view";
    }

    @GetMapping("/support/support-faq")
    public String support_faq(){
        return "/user/support/faq/support-faq";
    }

    @GetMapping("/support/support-lease")
    public String support_lease(){
        return "/user/support/lease/support-lease";
    }

    @GetMapping("/support/support-lost")
    public String support_lost(){
        return "/user/support/lost/support-lost";
    }

    @GetMapping("/support/support-news")
    public String support_news(){
        return "/user/support/news/support-news";
    }

    @GetMapping("/support/news/support-news-detail-view")
    public String support_news_detail_view(){
        return "/user/support/news/support-news-detail-view";
    }

    @GetMapping("/support/support-qna")
    public String support_qna(){
        return "/user/support/qna/support-qna";
    }
}
