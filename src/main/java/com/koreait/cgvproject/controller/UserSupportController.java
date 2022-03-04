package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserSupportController {

    private final String ROOT = "user/support";

    @GetMapping("/support")
    public String support(){
        return ROOT + "/support";
    }

    @GetMapping("/support/faq/support-faq-detail-view")
    public String support_faq_detail_view(){
        return  ROOT + "/faq/support-faq-detail-view";
    }

    @GetMapping("/support/support-faq")
    public String support_faq(){
        return ROOT + "/faq/support-faq";
    }

    @GetMapping("/support/support-lease")
    public String support_lease(){
        return ROOT + "/lease/support-lease";
    }

    @GetMapping("/support/support-lost")
    public String support_lost(){
        return ROOT + "/lost/support-lost";
    }

    @GetMapping("/support/support-news")
    public String support_news(){
        return ROOT + "/news/support-news";
    }

    @GetMapping("/support/news/support-news-detail-view")
    public String support_news_detail_view(){
        return ROOT + "/news/support-news-detail-view";
    }

    @GetMapping("/support/support-qna")
    public String support_qna(){
        return ROOT + "/qna/support-qna";
    }
}
