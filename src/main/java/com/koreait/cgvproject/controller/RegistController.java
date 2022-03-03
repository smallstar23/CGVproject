package com.koreait.cgvproject.controller;

import com.koreait.cgvproject.dto.Member_info_DTO;
import com.koreait.cgvproject.entity.MemberinfoEntity;
import com.koreait.cgvproject.repository.MemberinfoRepository;

import com.koreait.cgvproject.service.MemberinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class RegistController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결
    private MemberinfoRepository memberinfoRepository;

    @Autowired
    private MemberinfoService memberinfoService;

    @GetMapping("/user/agreement")
    public String agreement(){
        return "user/regist/agreement";
    }


//    @GetMapping("/user/finish_do")
//    public String finish_do(Model model, Long id){
//        MemberinfoEntity member = memberinfoService.viewuserid(id);
//        model.addAttribute("form",memberinfoService.viewuserid(id));
//
//        return "user/regist/finish_do";
//    }

    @GetMapping("/user/finish_do")
    public String finish_do(){

        return "user/regist/finish_do";
    }

    @GetMapping("/user/join")
    public String join(){
        return "user/regist/join";
    }

    @GetMapping("/user/member_info")
    public String member_info(){
        return "user/regist/member_info";
    }

    @PostMapping("/user/finish_do")
    public String createMemberinfo(Member_info_DTO memberinfoDTO){

        memberinfoService.regist(memberinfoDTO);

        return "user/regist/finish_do";
    }

}
