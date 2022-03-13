package com.koreait.cgvproject.service.user.login;


import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserLoginService{

    MemberRepository memberRepository;

  public boolean login(String userid, String userpw){
      Member member =  memberRepository.findByUserid(userid);

     if(member!=null&&member.getUserpw().equals(userpw)) return true;

     return false;
  }


}
