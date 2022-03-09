package com.koreait.cgvproject.service.admin.theater;

import com.koreait.cgvproject.dto.HallDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminTheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    // 상영관 코드 정보 받기
    public List<TheaterDTO> getCGV(Long acode) {
        List<Theater> theaterlist = theaterRepository.findAllByAreacode(acode);
        List<TheaterDTO> cgvDTOlist = new ArrayList<>();
        for (Theater theater : theaterlist) cgvDTOlist.add(theater.toDTO());
        return cgvDTOlist;
    }

    public TheaterDTO getTheater(Long tcode) {
        Theater theater = theaterRepository.findByTcode(tcode);
        System.out.println(theater);
        TheaterDTO findtheaterDTO = theater.toDTO();
        System.out.println(findtheaterDTO);
        return findtheaterDTO;

    }


    public List<TheaterDTO> findall() {
        List<Theater> theaterList=theaterRepository.findAll();
        List<TheaterDTO> theaterDTOList= new ArrayList<>();
        for(Theater theater: theaterList){
            theaterDTOList.add(theater.toDTO());
        }
        return theaterDTOList;

    }
    public List<HallDTO> getHallList(Long tcode) {
        System.out.println(tcode);
        Theater theater=theaterRepository.findByTcode(tcode);
        System.out.println(theater);
        List<HallDTO> hallDTOList=new ArrayList<>();
        if(theater!=null){
            theater.getHalls().stream().forEach(hall -> {
                hallDTOList.add(hall.toDTO());

            });
        }
        return hallDTOList;
    }


}