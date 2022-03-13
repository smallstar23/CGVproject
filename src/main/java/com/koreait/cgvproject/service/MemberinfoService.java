package com.koreait.cgvproject.service;

import com.koreait.cgvproject.model.entity.Member;
import com.koreait.cgvproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberinfoService {

    @Autowired
    MemberRepository memberRepository;

//    public void regist(MemberDTO memberDTO) {
////        System.out.println(memberinfoDTO.toString()); -> 로깅으로 변환(로깅은 자동차의 블랙박스와 비슷한 역할)
//        log.info(memberDTO.toString());
//
//        // 1. DTO를 변환 -> Entity로
//        Member member = memberDTO.toEntity();
////        System.out.println(member.toString());
//        log.info(member.toString());
//
//        //2. Repository에게 Entity를 DB안에 저장하게 함!
//        Member saved = memberRepository.save(member);
////        System.out.println(saved.toString());
//        log.info(saved.toString());
//    }

//    public void idCheck(Long id){
//
//        return memberinfoRepository.findById(id).get();
//    }

    public int idCheck(String userid) {
        Member check = memberRepository.findByUserid(userid);
        try {
            if (check != null) {
                return 1;
            }
        } catch (NonUniqueResultException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}


