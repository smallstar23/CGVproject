package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.MemberDTO;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.repository.MemberRepository;

import com.koreait.cgvproject.service.MemberinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class UserRegistController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결
    private MemberRepository memberRepository;

    @Autowired
    private MemberinfoService memberinfoService;

    @GetMapping("/user/agreement")
    public String agreement(){
        return "user/regist/agreement";
    }


//    @GetMapping("/user/finish_do")
//    public String finish_do(Model model, Long id){
//        Member member = memberinfoService.viewuserid(id);
//        model.addAttribute("form",memberinfoService.viewuserid(id));
//
//        return "user/regist/finish_do";
//    }

    @GetMapping("/user/join")
    public String join(){
        return "user/regist/join";
    }

    @GetMapping("/user/member_info")
    public String member_info(){
        return "user/regist/member_info";
    }

    // 회원가입 완료페이지(finish_do)로 오면 regist 메소드를 통해 DB에 담기게 함
    @PostMapping("/user/finish_do")
    public String createMemberinfo(MemberDTO memberinfoDTO){
        // 서비스 이용 잠시 중지
        // memberinfoService.regist(memberinfoDTO);

        //        System.out.println(memberinfoDTO.toString()); -> 로깅으로 변환(로깅은 자동차의 블랙박스와 비슷한 역할)
        log.info(memberinfoDTO.toString());

        // 1. DTO를 변환 -> Entity로
        Member member = memberinfoDTO.toEntity();
//        System.out.println(member.toString());
        log.info(member.toString());

        //2. Repository에게 Entity를 DB안에 저장하게 함!
        Member saved = memberRepository.save(member);
//        System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/user/finish_do/" + saved.getIdx();
    }

    // 회원가입 완료페이지에서 유저 아이디를 가져올 수 있게 매핑
    @GetMapping("/user/finish_do/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id : " + id);

        // 1. ID로 데이터를 가져옴!
        Member member = memberRepository.findById(id).orElse(null);
//        Optional<Member> member = memberinfoRepository.findById(id);

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("memberinfo", member);

        // 3. 보여줄 페이지를 설정!
        return "user/regist/finish_do";
    }

    // 보안상 id 값을 숨겨야 하므로 user/finish_do/success 로 url을 redirect해서 넘길거임
//    @GetMapping("/user/finish_do")
//    public String finish_do(){
//
//        return "redirect:/user/regist/finish_do";
//    }



//    @PostMapping("/checkid")
//    public String checkid(@RequestParam String userid) {
//
////        String userid = req.substring(9);
////        Optional<Member> memberinfoEntity = memberinfoRepository.findById()
//        System.out.println(userid);
//        return "result";
//    }



}
