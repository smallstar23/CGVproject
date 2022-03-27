package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.FavCGVDTO;
import com.koreait.cgvproject.entity.FavCGV;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.repository.FavCGVRepository;
import com.koreait.cgvproject.repository.MemberRepository;
import com.koreait.cgvproject.service.user.login.UserLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginService userLoginService;

    @Autowired
    public HttpSession session;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FavCGVRepository favCGVRepository;

    @GetMapping("/user/login")
    public String login(){
        return "user/login/login";
    }

    @PostMapping("/main")
    @ResponseBody
    public int postMain(@RequestParam("userid") String userid, @RequestParam("userpw") String userpw){
        Member member = memberRepository.findByUserid(userid);
        List<FavCGV> favCGVList = favCGVRepository.findAllByMemIdx(member.getIdx());
        if(userLoginService.login(userid,userpw)) {
            session.setAttribute("idx", member.getIdx());
            session.setAttribute("userid", userid);
            session.setAttribute("username", member.getUsername()); // 주문자 정보 확인 위해서
            session.setAttribute("userhp",member.getHp()); // 주문자 정보 확인 위해서
            session.setAttribute("nickname", member.getNickname());
            session.setAttribute("valpoint", member.getValpoint());
            session.setAttribute("favCGV", favCGVList);
            return 1;
        }
        return 0;

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
