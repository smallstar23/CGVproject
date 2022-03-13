package com.koreait.cgvproject.controller.admin.page;


import com.koreait.cgvproject.model.dto.MemberDTO;
import com.koreait.cgvproject.model.entity.GiftPayment;
import com.koreait.cgvproject.model.entity.Member;
import com.koreait.cgvproject.repository.GiftPaymentRepository;
import com.koreait.cgvproject.repository.MemberRepository;

import com.koreait.cgvproject.service.admin.member.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@Slf4j
public class AdminMemberController {


    private MemberRepository memberRepository;

    @Autowired
    private GiftPaymentRepository giftPaymentRepository;

    private MemberService memberService;


    @GetMapping("member-lookup")//member-lookup 페이지 회원정보 조회
    public String member_lookup(Model model){
        List<MemberDTO> member__dtoList =memberService.getMemberList();
        model.addAttribute("member2DTOList", member__dtoList);
        return "/admin/member/member-lookup";
    }
    @PostMapping("/post")
    public  String write(MemberDTO member__dto){
        memberService.insertPost(member__dto);
        return  "redirect:/";
    }

    @GetMapping("/post/{idx}")
    public  String view(@PathVariable("idx") Long id,Model model){
        System.out.println("들어온 idx"+id);
        MemberDTO member__dto =memberService.getPost(id);
        model.addAttribute("post", member__dto);
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
    public String member_paymentlist(Model model){
        List<GiftPayment> giftPaymentList = giftPaymentRepository.findAll();

//        log.info(giftPaymentList.toString()); -> toString 계속 되는 오류뜸 ( 스택플로우오류)

        model.addAttribute("giftPaymentList",giftPaymentList);

        return "/admin/member/member-paymentlist";
    }

    @GetMapping("member-paymentList-view/{id}")//member-paymentlist-view 페이지
    public String member_paymentlist_view(@PathVariable Long id, Model model){

        List<GiftPayment> paymentlistView = giftPaymentRepository.findByMemberIdx(id);
        Member member = memberRepository.findById(id).orElse(null);

        model.addAttribute("paymentlistView",paymentlistView);
        model.addAttribute("member",member);

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
