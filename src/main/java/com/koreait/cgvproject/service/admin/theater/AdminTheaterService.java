package com.koreait.cgvproject.service.admin.theater;

import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminTheaterService {

    private TheaterRepository theaterRepository;

    // 상영관 이름으로 정보 받기
    public Theater getTheater(String tname){
        Theater theater=theaterRepository.findByTname(tname);
        return theater;

    }

}
