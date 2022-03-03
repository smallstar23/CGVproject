package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MycgvController {

    @GetMapping("/user/mycgv")
    public String mycgv(){
        return "user/mycgv/mycgv";
    }

    @GetMapping("/user/mycgv-cgvPoint")
    public String mycgv_cgvPoint(){
        return "user/mycgv/mycgv-cgvPoint";
    }

    @GetMapping("/user/mycgv-cgvPoint-pointInfo")
    public String mycgv_cgvPoint_pointInfo(){
        return "user/mycgv/mycgv-cgvPoint-pointInfo";
    }

    @GetMapping("/user/mycgv-cgvPoint-pointList")
    public String mycgv_cgvPoint_pointList(){
        return "user/mycgv/mycgv-cgvPoint-pointList";
    }

    @GetMapping("/user/mycgv-event")
    public String mycgv_event(){
        return "user/mycgv/mycgv-event";
    }

    @GetMapping("/user/mycgv-event-resultList")
    public String mycgv_event_resultList(){
        return "user/mycgv/mycgv-event-resultList";
    }

    @GetMapping("/user/mycgv-favoriteTheaters")
    public String mycgv_favoriteTheaters(){
        return "user/mycgv/mycgv-favoriteTheaters";
    }

    @GetMapping("/user/mycgv-lost-list")
    public String mycgv_lost_list(){
        return "user/mycgv/mycgv-lost-list";
    }

    @GetMapping("/user/mycgv-movielog-expected")
    public String mycgv_movielog_expected(){
        return "user/mycgv/mycgv-movielog-expected";
    }

    @GetMapping("/user/mycgv-movielog-mylist")
    public String mycgv_movielog_mylist(){
        return "user/mycgv/mycgv-movielog-mylist";
    }

    @GetMapping("/user/mycgv-movielog-watched")
    public String mycgv_movielog_watched(){
        return "user/mycgv/mycgv-movielog-watched";
    }

    @GetMapping("/user/mycgv-myinfo")
    public String mycgv_myinfo(){
        return "user/mycgv/mycgv-myinfo";
    }

    @GetMapping("/user/mycgv-myinfo-edit-myinfo")
    public String mycgv_myinfo_edit_myinfo(){
        return "user/mycgv/mycgv-myinfo-edit-myinfo";
    }

    @GetMapping("/user/mycgv-myinfo-edit-myinfo-myprofile")
    public String mycgv_myinfo_edit_myinfo_myprofile(){
        return "user/mycgv/mycgv-myinfo-edit-myinfo-myprofile";
    }

    @GetMapping("/user/mycgv-myinfo-leavecgv")
    public String mycgv_myinfo_leavecgv(){
        return "user/mycgv/mycgv-myinfo-leavecgv";
    }

    @GetMapping("/user/mycgv-popcorn-store")
    public String mycgv_popcorn_store(){
        return "user/mycgv/mycgv-popcorn-store";
    }

    @GetMapping("/user/mycgv-popcorn-store-paymentList")
    public String mycgv_popcorn_store_paymentList(){
        return "user/mycgv/mycgv-popcorn-store-paymentList";
    }

    @GetMapping("/user/mycgv-popupdedit-profile")
    public String mycgv_popupedit_profile(){
        return "user/mycgv/mycgv-popupdedit-profile";
    }

    @GetMapping("/user/mycgv-qna-list")
    public String mycgv_qna_list(){
        return "user/mycgv/mycgv";
    }

    @GetMapping("/user/mycgv-reserve")
    public String mycgv_reserve(){
        return "user/mycgv/mycgv-reserve";
    }

}
