package com.koreait.cgvproject.service.user.mycgv;

import com.koreait.cgvproject.dto.PointDTO;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.entity.Point;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.MemberRepository;
import com.koreait.cgvproject.repository.PointRepository;
import com.koreait.cgvproject.repository.TheaterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserPointService {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public List<PointDTO> getPointList(){
        List<PointDTO> pointDTOList = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();
        List<Theater> theaterList = theaterRepository.findAll();
        List<Point> pointList = new ArrayList<>();

        for(Member member:memberList){
            for(Theater theater:theaterList){
                pointList = pointRepository.findAllByMemberAndTheater(member, theater);
                if(pointList!=null){
                    for(Point point:pointList){
                        PointDTO pointDTO = new PointDTO();
                        pointDTO.setValPoint(point.getValPoint());
                        pointDTO.setPointChange(point.getPointChange());
                        pointDTO.setTheaterDTO(point.getTheater().toDTO());
                        pointDTO.setMemberDTO(point.getMember().toDTO());
                        pointDTO.setKind(point.getKind());
                        pointDTOList.add(pointDTO);
                    }
                }
            }
        }
        return pointDTOList;
    }

    public void addPoint(PointDTO pointDTO){
        Point point = new Point();
        Member member = memberRepository.findByIdx(pointDTO.getMem_idx());
        Theater theater = theaterRepository.findByTcode(pointDTO.getTcode());
        if(member!=null || theater!=null){
            point.setPointChange(pointDTO.getPointChange());
            point.setPcode(pointDTO.getPcode1()+pointDTO.getPcode2()+pointDTO.getPcode3());
            point.setTheater(theater);
            point.setMember(member);
            point.setValPoint(pointDTO.getValPoint());
            point.setKind(pointDTO.getKind());
            point.setRegDate(pointDTO.getRegDate());
            pointRepository.save(point);

        }

    }

}
















