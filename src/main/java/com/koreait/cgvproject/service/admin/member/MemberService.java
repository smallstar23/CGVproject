package com.koreait.cgvproject.service.admin.member;

import com.koreait.cgvproject.dto.MemberDTO;
import com.koreait.cgvproject.entity.Member;
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

    public List<MemberDTO> getMemberList(){

        List<MemberDTO> member__dtoList =new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();
        for(Member member : memberList){
         MemberDTO member__dto = MemberDTO.builder()
                 .idx(member.getIdx())
                 .userid(member.getUserid())
                 .username(member.getUsername())
                 .userpw(member.getUserpw())
                 .hp1(member.getHp())
                 .ssn1(member.getSsn())
                 .email1(member.getEmail())
                 .nickname(member.getNickname())
                 .regDate(member.getRegDate())
                 .build();
            member__dtoList.add(member__dto);
        }
        return member__dtoList;
    }


    @Transactional
    public void insertPost(MemberDTO member__dto){
        memberRepository.save(member__dto.toadminMember()).getIdx();
    }


    @Transactional
    public MemberDTO getPost(Long idx){
        Member member = memberRepository.findById(idx).get();

        MemberDTO member__dto = MemberDTO.builder()
                .idx(member.getIdx())
                .userid(member.getUserid())
                .username(member.getUsername())
                .userpw(member.getUserpw())
                .hp1(member.getHp())
                .email1(member.getEmail())
                .ssn1(member.getSsn())
                .nickname(member.getNickname())
                .regDate(member.getRegDate())
                .build();
        return member__dto;

    }
}
