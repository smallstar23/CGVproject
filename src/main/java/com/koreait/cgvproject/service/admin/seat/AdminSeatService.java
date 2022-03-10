package com.koreait.cgvproject.service.admin.seat;

import com.koreait.cgvproject.dto.SeathtmlDTO;
import com.koreait.cgvproject.entity.Seathtml;
import com.koreait.cgvproject.repository.SeatHtmlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminSeatService {

    private SeatHtmlRepository seatHtmlRepository;

    public boolean seatHtmlExist(Long hcode){
        Optional<Seathtml> seathtml = seatHtmlRepository.findByHcode(hcode);
        if(seathtml.isPresent()) return true;
        else return false;
    }
    public void seatHtmlCreate(SeathtmlDTO seathtmlDTO){
        seatHtmlRepository.save(seathtmlDTO.toEntity());
    }
}
