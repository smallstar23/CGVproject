package com.koreait.cgvproject.service;

import com.koreait.cgvproject.dto.Member_info_DTO;
import com.koreait.cgvproject.entity.MemberinfoEntity;
import com.koreait.cgvproject.repository.MemberinfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberinfoService {

    @Autowired
    MemberinfoRepository memberinfoRepository;

    public void regist(Member_info_DTO memberinfoDTO){
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
    }
    public MemberinfoEntity viewuserid(Long id){

        return memberinfoRepository.findById(id).get();
    }

}
