package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.repository.MemberRepository;
import com.koreait.cgvproject.service.user.login.UserLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginService userLoginService;

    @Autowired
    private HttpSession session;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/user/login")
    public String login(){
        return "user/login/login";
    }

    @PostMapping("/main")
    public String postMain(@RequestParam String userid, @RequestParam String userpw){
        Member member = memberRepository.findByUserid(userid);
        if(userLoginService.login(userid,userpw)) {
            session.setAttribute("userid", userid);
            session.setAttribute("username", member.getUsername()); // 주문자 정보 확인 위해서
            session.setAttribute("userhp",member.getHp()); // 주문자 정보 확인 위해서
            session.setAttribute("nickname", member.getNickname());
            session.setAttribute("valpoint", member.getValpoint());
            return "/user/main"; //메인
        }
        return "redirect:/user/login"; //다시 로그인

    }

    @GetMapping("/user/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/main";
    }

    @GetMapping("/user/login-agreement")
    public String login_agreement(){
        return "user/login/login-agreement";
    }

    @GetMapping("/user/login-guest-hometicket")
    public String login_guest_hometicket(){
        return "user/login/login-guest-hometicket";
    }

    @GetMapping("/user/login-guest-login")
    public String login_guest_login(){
        return "user/login/login-guest-login";
    }

    @GetMapping("/user/login-guest-reserve")
    public String login_guest_reserve(){
        return "user/login/login-guest-reserve";
    }
}
