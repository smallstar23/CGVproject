package com.koreait.cgvproject.service.admin;

import com.koreait.cgvproject.entity.Seathtml;
import com.koreait.cgvproject.repository.SeatHtmlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminSeatService {

    private SeatHtmlRepository seatHtmlRepository;

    public Integer seatHtmlExist(Long hcode){
        Optional<Seathtml> seathtml = seatHtmlRepository.findByHcode(hcode);
        if(seathtml.isPresent()) return 1;
        else return 0;
    }
}
