package com.koreait.cgvproject.controller;

import com.koreait.cgvproject.dto.Member_info_DTO;
import com.koreait.cgvproject.entity.MemberinfoEntity;
import com.koreait.cgvproject.repository.MemberinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Member_info_Controller {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결
    private MemberinfoRepository memberinfoRepository;

    @GetMapping("/user/member_info")
    public String newMemberinfoForm(){
        return "user/member_info";
    }

    @PostMapping("/member_info/create")
    public String createMemberinfo(Member_info_DTO memberinfoDTO){
        System.out.println(memberinfoDTO.toString());

        // 1. DTO를 변환 -> Entity로
        MemberinfoEntity memberinfoEntity = memberinfoDTO.toEntity();
        System.out.println(memberinfoEntity.toString());

        //2. Repository에게 Entity를 DB안에 저장하게 함!
        MemberinfoEntity saved = memberinfoRepository.save(memberinfoEntity);
        System.out.println(saved.toString());
        return "";
    }
}
