package com.koreait.cgvproject.service.admin.member;

import com.koreait.cgvproject.dto.MemberDTO;
import com.koreait.cgvproject.entity.Gift;
import com.koreait.cgvproject.entity.GiftPayment;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.repository.GiftPaymentRepository;
import com.koreait.cgvproject.repository.MemberRepository;
import com.koreait.cgvproject.repository.PointRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService {

    private final HttpSession session;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GiftPaymentRepository giftPaymentRepository;

    @Autowired
    private PointRepository pointRepository;

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
        Member member = memberRepository.findByUserid((String)session.getAttribute("userid"));
        giftPaymentRepository.deleteByMember(giftPaymentRepository.findByMember(member.getIdx()));
        memberRepository.deleteById(idx);

    }

    public void deleteUser(Long idx) {
        Optional<Member> member = memberRepository.findById(idx);

        if(giftPaymentRepository.existsGiftPaymentByMemberIdx(member.get().getIdx())){
            List<GiftPayment> giftPayments = giftPaymentRepository.findByMemberIdx(member.get().getIdx());
            for(int i=0; i<giftPayments.size(); i++){
                giftPaymentRepository.deleteById(giftPayments.get(i).getGpcode());
            }
        }

        if(giftPaymentRepository.existsGiftPaymentByMemberIdx(member.get().getIdx())){
            List<GiftPayment> giftPayments = giftPaymentRepository.findByMemberIdx(member.get().getIdx());
            for(int i=0; i<giftPayments.size(); i++){
                giftPaymentRepository.deleteById(giftPayments.get(i).getGpcode());
            }
        }

//        if(boardCommentRepository.existsByWriter(memberEntity.get())){
//            List<BoardCommentEntity> boardCommentEntityList = boardCommentRepository.findByWriter(memberEntity.get());
//            for(int i=0; i<boardCommentEntityList.size();i++){
//                boardCommentQueryRepository.deleteByCid(boardCommentEntityList.get(i).getId());
//            }
//        }

        memberRepository.deleteById(idx);
    }
}
