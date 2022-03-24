package com.koreait.cgvproject.service.user.mycgv;

import com.koreait.cgvproject.dto.FavCGVDTO;
import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.*;
import com.koreait.cgvproject.repository.FavCGVRepository;
import com.koreait.cgvproject.repository.MemberRepository;
import com.koreait.cgvproject.repository.TheaterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserFavCGVService {

    HttpSession session;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FavCGVRepository favCGVRepository;

    public void insert(FavCGVDTO favCGVDTO, Member member){
        FavCGV favCGV = new FavCGV();
        favCGV.setMemIdx(member.getIdx());
        favCGV.setTname(favCGVDTO.getTname());
        favCGV.setAreacode(favCGVDTO.getAreacode());
        favCGVRepository.save(favCGV);
    }

    public List<FavCGVDTO> list(Member member){
        List<FavCGVDTO> favCGVDTOS =new ArrayList<>();
        List<FavCGV> favCGVList = favCGVRepository.findByMemberIdx(member.getIdx());
        for(FavCGV favCGV : favCGVList){
            favCGVDTOS.add(favCGV.toDTO());
        }
        return favCGVDTOS;
    }

}
