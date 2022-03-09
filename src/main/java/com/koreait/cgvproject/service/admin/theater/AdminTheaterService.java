package com.koreait.cgvproject.service.admin.theater;

import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminTheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    // 극장 코드 정보 받기
    public List<TheaterDTO> getCGV(Long acode){
        List<Theater> theaterlist=theaterRepository.findAllByAreacode(acode);
        List<TheaterDTO> cgvDTOlist = new ArrayList<>();
        for(Theater theater : theaterlist) cgvDTOlist.add(theater.toDTO());
        return cgvDTOlist;
    }

    public TheaterDTO getTheater(Long tcode){
        Theater theater=theaterRepository.findByTcode(tcode);
        System.out.println(theater);
        TheaterDTO findtheaterDTO=theater.toDTO();
        System.out.println(findtheaterDTO);
        return findtheaterDTO;

    }

}
