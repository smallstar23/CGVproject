package com.koreait.cgvproject.controller.user.rest;

import com.koreait.cgvproject.service.MemberinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRegistRestController {

    @Autowired
    private MemberinfoService memberinfoService;

    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("userid") String userid){

        System.out.println("전달받은 userid:"+userid);
        if(memberinfoService.idCheck(userid)==1){
            System.out.println("같은 아이디가 존재합니다.");
            return 1;
        }
        if(userid.length() < 4 || userid.length() > 19){
            return 2;
        }

        return 0;
    }

}
