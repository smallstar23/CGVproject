package com.koreait.cgvproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminMemberController {


    @GetMapping("member-lookup")//member-lookup 페이지
    public String member_lookup(){
        return "/admin/member/member-lookup";
    }
    @GetMapping("manage_user_view")//manage_user_view 페이지
    public String manage_user_view(){
        return "/admin/member/manage_user_view";
    }

    @GetMapping("member-ticket")//member-ticket 페이지
    public String member_ticket(){
        return "/admin/member/member-ticket";
    }
    @GetMapping("member-paymentlist")//member-paymentlist 페이지
    public String member_paymentlist(){
        return "/admin/member/member-paymentlist";
    }
    @GetMapping("member-pointlist")//member-pointlist 페이지
    public String member_pointlist(){
        return "/admin/member/member-pointlist";
    }
}
