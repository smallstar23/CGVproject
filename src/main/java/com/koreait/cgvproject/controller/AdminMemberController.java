package com.koreait.cgvproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminMemberController {


    @GetMapping("/member/member-lookup")//member-lookup 페이지 회원정보 조회
    public String member_lookup(){
        return "/admin/member/member-lookup";
    }

    @GetMapping("/member/member_user_view")//manage_user_view 페이지
    public String member_user_view(){
        return "/admin/member/member_user_view";
    }

    @GetMapping("/member/member-ticket")//member-ticket 페이지 예매내역 조회
    public String member_ticket(){
        return "/admin/member/member-ticket";
    }

    @GetMapping("member-ticket-view")//member-ticket-view 페이지
    public String member_ticket_view(){
        return "/admin/member/member_ticketing_view";
    }


    @GetMapping("/member/member-paymentlist")//member-paymentlist 페이지 결제내역 조회
    public String member_paymentlist(){
        return "/admin/member/member-paymentlist";
    }

    @GetMapping("/member/member-paymentlist-view")//member-paymentlist-view 페이지
    public String member_paymentlist_view(){
        return "/admin/member/member-paymentlist-view";
    }


    @GetMapping("/member/member-pointlist")//member-pointlist 페이지 포인트 내역 조회
    public String member_pointlist(){
        return "/admin/member/member-pointlist";
    }


    @GetMapping("/member/member-pointlist-view")//member-pointlist-view 페이지
    public String member_pointlist_view(){
        return "/admin/member/member-pointlist-view";
    }
}
