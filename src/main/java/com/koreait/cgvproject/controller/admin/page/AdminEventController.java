package com.koreait.cgvproject.controller.admin.page;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminEventController {

    @GetMapping("event-list-special")//event-list-special 페이지
    public  String event_list_special(){
        return  "admin/event/event-list-special";
    }

    @GetMapping("event-list-movie")//event-list-special 페이지
    public  String event_list_movie(){
        return  "admin/event/event-list-movie";
    }

    @GetMapping("event-list-discount")//event-list-special 페이지
    public  String event_list_discount(){
        return  "admin/event/event-list-discount";
    }

    @GetMapping("event-list-CLUB")//event-list-special 페이지
    public  String event_list_CLUB(){
        return  "admin/event/event-list-CLUB";
    }
    @GetMapping("event-list-CGV")//event-list-special 페이지
    public  String event_list_CGV(){
        return  "admin/event/event-list-CGV";
    }


    @GetMapping("event-regist")//event-regist 페이지
    public  String event_regist(){
        return  "admin/event/event-regist";
    }

    @GetMapping("event-result")//event-result 페이지
    public  String event_result(){
        return  "admin/event/event-result";
    }

    @GetMapping("event-result-regist")//event-result 페이지
    public  String event_result_regist(){
        return  "admin/event/event-result-regist";
    }

    @GetMapping("event-result-view")//event-result 페이지
    public  String event_result_view(){
        return  "admin/event/event-result_view";
    }

    @GetMapping("event-done")//event-done 페이지
    public  String event_done(){
        return  "admin/event/event-done";
    }

    @GetMapping("event-view")//event-view 페이지
    public  String event_view(){
        return  "admin/event/event-view";
    }
}
