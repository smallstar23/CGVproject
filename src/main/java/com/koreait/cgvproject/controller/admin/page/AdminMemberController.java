package com.koreait.cgvproject.controller.admin.page;


import com.koreait.cgvproject.dto.Member_info_DTO;
import com.koreait.cgvproject.service.admin.member.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class AdminMemberController {

    private MemberService memberService;


    @GetMapping("member-lookup")//member-lookup 페이지 회원정보 조회
    public String member_lookup(Model model){
        List<Member_info_DTO> member_info_dtoList =memberService.getMemberList();
        model.addAttribute("member2DTOList",member_info_dtoList);
        return "/admin/member/member-lookup";
    }
    @PostMapping("/post")
    public  String write(Member_info_DTO member_info_dto){
        memberService.insertPost(member_info_dto);
        return  "redirect:/";
    }

    @GetMapping("/post/{idx}")
    public  String view(@PathVariable("idx") Long id,Model model){
        Member_info_DTO member_info_dto =memberService.getPost(id);
        model.addAttribute("post",member_info_dto);
        return "admin/member/member_user_view";
    }



    @GetMapping("member_user_view")//manage_user_view 페이지
    public String member_user_view(){
        return "/admin/member/member_user_view";
    }

    @GetMapping("member-ticket")//member-ticket 페이지 예매내역 조회
    public String member_ticket(){
        return "/admin/member/member-ticket";
    }

    @GetMapping("member-ticketing-view")//member-ticket-view 페이지
    public String member_ticket_view(){
        return "/admin/member/member_ticketing_view";
    }


    @GetMapping("member-paymentlist")//member-paymentlist 페이지 결제내역 조회
    public String member_paymentlist(){
        return "/admin/member/member-paymentlist";
    }

    @GetMapping("member-paymentlist-view")//member-paymentlist-view 페이지
    public String member_paymentlist_view(){
        return "/admin/member/member-paymentlist-view";
    }


    @GetMapping("member-pointlist")//member-pointlist 페이지 포인트 내역 조회
    public String member_pointlist(){
        return "/admin/member/member-pointlist";
    }


    @GetMapping("member-pointlist-view")//member-pointlist-view 페이지
    public String member_pointlist_view(){
        return "/admin/member/member-pointlist-view";
    }
}
