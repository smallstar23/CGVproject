package com.koreait.cgvproject.controller;

import com.koreait.cgvproject.dto.Member_info_DTO;
import com.koreait.cgvproject.entity.MemberinfoEntity;
import com.koreait.cgvproject.repository.MemberinfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class RegistController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결
    private MemberinfoRepository memberinfoRepository;

    @GetMapping("/user/agreement")
    public String agreement(){
        return "user/regist/agreement";
    }

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
//        System.out.println(memberinfoDTO.toString()); -> 로깅으로 변환(로깅은 자동차의 블랙박스와 비슷한 역할)
        log.info(memberinfoDTO.toString());

        // 1. DTO를 변환 -> Entity로
        MemberinfoEntity memberinfoEntity = memberinfoDTO.toEntity();
//        System.out.println(memberinfoEntity.toString());
        log.info(memberinfoEntity.toString());

        //2. Repository에게 Entity를 DB안에 저장하게 함!
        MemberinfoEntity saved = memberinfoRepository.save(memberinfoEntity);
//        System.out.println(saved.toString());
        log.info(saved.toString());

        return "user/regist/finish_do";
    }

}
