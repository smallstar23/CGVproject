package com.koreait.cgvproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserMycgvController {

    @GetMapping("/user/mycgv")
    public String mycgv(){
        return "user/mycgv/mycgv";
    }

    @GetMapping("/user/mycgv/point")
    public String mycgv_cgvPoint(){
        return "user/mycgv/point/mycgv-cgvPoint";
    }

    @GetMapping("/user/mycgv/point/mycgv-cgvPoint-pointInfo")
    public String mycgv_cgvPoint_pointInfo(){
        return "user/mycgv/point/mycgv-cgvPoint-pointInfo";
    }

    @GetMapping("/user/mycgv/point/mycgv-cgvPoint-pointList")
    public String mycgv_cgvPoint_pointList(){
        return "user/mycgv/point/mycgv-cgvPoint-pointList";
    }

    @GetMapping("/user/mycgv/event")
    public String mycgv_event(){
        return "user/mycgv/event/mycgv-event";
    }

    @GetMapping("/user/mycgv/event/mycgv-event-resultList")
    public String mycgv_event_resultList(){
        return "user/mycgv/event/mycgv-event-resultList";
    }

    @GetMapping("/user/popup/mycgv-favoriteTheaters")
    public String mycgv_favoriteTheaters(){
        return "user/mycgv/popup/mycgv-favoriteTheaters";
    }

    @GetMapping("/user/mycgv/inquiry/mycgv-lost-list")
    public String mycgv_lost_list(){
        return "user/mycgv/inquiry/mycgv-lost-list";
    }

    @GetMapping("/user/mycgv-movielog-expected")
    public String mycgv_movielog_expected(){
        return "user/mycgv/movielog/mycgv-movielog-expected";
    }

    @GetMapping("/movies/point/mycgv-movielog-mylist")
    public String mycgv_movielog_mylist(){
        return "user/mycgv/movielog/mycgv-movielog-mylist";
    }

    @GetMapping("/user/movielog/mycgv-movielog-watched")
    public String mycgv_movielog_watched(){
        return "user/mycgv/movielog/mycgv-movielog-watched";
    }

    @GetMapping("/user/mycgv/mycgv-myinfo")
    public String mycgv_myinfo(){
        return "user/mycgv/myinfo/mycgv-myinfo";
    }

    @GetMapping("/user/mycgv/myinfo/mycgv-myinfo-edit-myinfo")
    public String mycgv_myinfo_edit_myinfo(){
        return "user/mycgv/myinfo/mycgv-myinfo-edit-myinfo";
    }

    @GetMapping("/user/mycgv/myinfo/mycgv-myinfo-edit-myinfo-myprofile")
    public String mycgv_myinfo_edit_myinfo_myprofile(){return "user/mycgv/myinfo/mycgv-myinfo-edit-myinfo-myprofile";}

    @GetMapping("/user/mycgv/myinfo/mycgv-myinfo-leavecgv")
    public String mycgv_myinfo_leavecgv(){
        return "user/mycgv/myinfo/mycgv-myinfo-leavecgv";
    }

    @GetMapping("/user/mycgv/mycgv-popcorn-store")
    public String mycgv_popcorn_store(){
        return "user/mycgv/popcorn-store/mycgv-popcorn-store";
    }

    @GetMapping("/user/mycgv/popcorn-store/mycgv-popcorn-store-paymentList")
    public String mycgv_popcorn_store_paymentList(){return "user/mycgv/popcorn-store/mycgv-popcorn-store-paymentList";}

    @GetMapping("/user/popup/mycgv-popupedit-profile")
    public String mycgv_popupedit_profile(){
        return "user/mycgv/popup/mycgv-popupedit-profile";
    }

    @GetMapping("/user/mycgv/inquiry/mycgv-qna-list")
    public String mycgv_qna_list(){
        return "user/mycgv/inquiry/mycgv-qna-list";
    }

    @GetMapping("/user/mycgv/mycgv-reserve")
    public String mycgv_reserve(){
        return "user/mycgv/reserve/mycgv-reserve";
    }

}
