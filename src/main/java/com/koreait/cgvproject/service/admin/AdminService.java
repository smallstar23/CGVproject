package com.koreait.cgvproject.service.admin;

import com.koreait.cgvproject.dto.HallDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.HallRepository;
import com.koreait.cgvproject.repository.TheaterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private TheaterRepository theaterRepository;
    private HallRepository hallRepository;

    public List<TheaterDTO> findTheaterByAreacode(Long areacode) {
        List<TheaterDTO> theaterDTOList = new ArrayList<>();
        List<Theater> theaters = theaterRepository.findAllByAreacode(areacode);
        theaters.forEach(theater -> theaterDTOList.add(theater.toDTO()));
        return theaterDTOList;
    }

    public List<HallDTO> findHallByTcode(Long tcode) {
        List<HallDTO> hallDTOList = new ArrayList<>();
        List<Hall> hallList = new ArrayList<>();

        Theater theater = theaterRepository.findByTcode(tcode);
        if (theater != null) {
            hallList = hallRepository.findAllByTheaterOrderByHcode(theater);
        }

        hallList.forEach(hall -> {
            hallDTOList.add(hall.toDTO());
        });

        return hallDTOList;
    }
}
