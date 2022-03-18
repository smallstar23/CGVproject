package com.koreait.cgvproject.service.admin.member;

import com.koreait.cgvproject.dto.MemberDTO;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService {
    @Autowired
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


    public Member getMember(String userid){
        Member member = memberRepository.findByUserid(userid);
        return member;
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

    public void update(Long idx,MemberDTO memberDTO){
        Optional<Member> member =memberRepository.findById(idx);
        member.ifPresent(select ->{
            select.setUserid(memberDTO.getUserid());
            select.setUsername(memberDTO.getUsername());
            select.setUserpw(memberDTO.getUserpw());
            select.setEmail(memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
            select.setHp(memberDTO.getHp1()+"-"+memberDTO.getHp2()+"-"+memberDTO.getHp3());
            select.setSsn(memberDTO.getSsn1().substring(2)+memberDTO.getSsn2()+memberDTO.getSsn3());
            select.setNickname(memberDTO.getNickname());
        });
        memberRepository.save(member.get());
    }

    public void delete(Long idx){
        log.info("idx="+idx);
        memberRepository.deleteById(idx);
    }
}
