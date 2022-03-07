package com.koreait.cgvproject.service.admin.member;

import com.koreait.cgvproject.dto.Member_info_DTO;
import com.koreait.cgvproject.entity.MemberinfoEntity;
import com.koreait.cgvproject.repository.MemberinfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberinfoRepository memberinfoRepository;

    public List<Member_info_DTO> getMemberList(){

        List<Member_info_DTO> member_info_dtoList =new ArrayList<>();
        List<MemberinfoEntity> memberinfoEntityList =memberinfoRepository.findAll();
        for(MemberinfoEntity memberinfoEntity : memberinfoEntityList){
         Member_info_DTO member_info_dto=Member_info_DTO.builder()
                 .idx(memberinfoEntity.getIdx())
                 .userid(memberinfoEntity.getUserid())
                 .username(memberinfoEntity.getUsername())
                 .userpw(memberinfoEntity.getUserpw())
                 .hp1(memberinfoEntity.getHp())
                 .ssn1(memberinfoEntity.getSsn())
                 .email1(memberinfoEntity.getEmail())
                 .nickname(memberinfoEntity.getNickname())
                 .regdate(memberinfoEntity.getRegdate())
                 .build();
            member_info_dtoList.add(member_info_dto);
        }
        return member_info_dtoList;
    }


    @Transactional
    public void insertPost(Member_info_DTO member_info_dto){
        memberinfoRepository.save(member_info_dto.toadminMember()).getIdx();
    }


    @Transactional
    public Member_info_DTO getPost(Long idx){
        MemberinfoEntity memberinfoEntity =memberinfoRepository.findById(idx).get();

        Member_info_DTO member_info_dto =Member_info_DTO.builder()
                .idx(memberinfoEntity.getIdx())
                .userid(memberinfoEntity.getUserid())
                .username(memberinfoEntity.getUsername())
                .userpw(memberinfoEntity.getUserpw())
                .hp1(memberinfoEntity.getHp())
                .email1(memberinfoEntity.getEmail())
                .ssn1(memberinfoEntity.getSsn())
                .nickname(memberinfoEntity.getNickname())
                .regdate(memberinfoEntity.getRegdate())
                .build();
        return member_info_dto;

    }
}
