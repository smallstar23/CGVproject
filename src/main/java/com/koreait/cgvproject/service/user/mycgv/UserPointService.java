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
                        pointDTO.setValpoint(point.getValpoint());
                        pointDTO.setPointchange(point.getPointchange());
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
            point.setPointchange(pointDTO.getPointchange());
            point.setPcode(pointDTO.getPcode());
            point.setValpoint(member.getValpoint());
            point.setKind(pointDTO.getKind());
            pointRepository.save(point);

        }

    }

}
















