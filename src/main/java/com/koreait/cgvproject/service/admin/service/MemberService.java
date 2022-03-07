package com.koreait.cgvproject.service.admin.service;

import com.koreait.cgvproject.dto.Member2DTO;
import com.koreait.cgvproject.entity.Member2;
import com.koreait.cgvproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    public List<Member2DTO> getMember2List(){
        List<Member2> member2List =memberRepository.findAll();
        List<Member2DTO> member2DTOList =new ArrayList<>();

        for(Member2 member2 : member2List){
            Member2DTO member2DTO=Member2DTO.builder()
                    .id(member2.getId())
                    .userid(member2.getUserid())
                    .password(member2.getPassword())
                    .hp(member2.getHp())
                    .address(member2.getAddress())
                    .age(member2.getAge())
                    .point(member2.getPoint())
                    .regdate(member2.getRegdate())
                    .build();
            member2DTOList.add(member2DTO);
        }
        return member2DTOList;
    }

    @Transactional
    public void insertPost(Member2DTO memberDTO){
        memberRepository.save(memberDTO.toEntity());
    }

    @Transactional
    public Member2DTO getPost(Long id){
        Member2 member2 =memberRepository.findById(id).get();

        Member2DTO member2DTO =Member2DTO.builder()
                .id(member2.getId())
                .userid(member2.getUserid())
                .password(member2.getPassword())
                .hp(member2.getHp())
                .address(member2.getAddress())
                .age(member2.getAge())
                .point(member2.getPoint())
                .regdate(member2.getRegdate())
                .build();
        return member2DTO;

    }
}
