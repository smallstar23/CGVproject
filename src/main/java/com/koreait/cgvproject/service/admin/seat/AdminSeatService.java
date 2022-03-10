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

    public boolean seatHtmlExist(Long hcode) {
        Optional<Seathtml> seathtml = seatHtmlRepository.findByHcode(hcode);
        return seathtml.isPresent();
    }

    public void seatHtmlCreate(SeathtmlDTO seathtmlDTO) {
        seatHtmlRepository.save(seathtmlDTO.toEntity());
    }
    public void seatHtmlUpdate(SeathtmlDTO seathtmlDTO){
        Optional<Seathtml> seathtml = seatHtmlRepository.findById(seathtmlDTO.getStIdx());
        seathtml.ifPresent(sHtml -> {
            sHtml.setStCol(seathtmlDTO.getStCol());
            sHtml.setStRow(seathtmlDTO.getStRow());
            sHtml.setRowEmpty(seathtmlDTO.getRowEmpty());
            sHtml.setColEmpty(seathtmlDTO.getColEmpty());
            seatHtmlRepository.save(sHtml);
        });
    }
    public SeathtmlDTO seatHtmlRead(Long hcode) {
        Optional<Seathtml> seatHtml = seatHtmlRepository.findByHcode(hcode);
        return seatHtml.map(Seathtml::toDTO).orElse(new SeathtmlDTO());

//        if (seatHtml.isPresent()) return seatHtml.get().toDTO();
//        return new SeathtmlDTO();
    }

    public void seatHtmlDelete(Long stIdx){
        seatHtmlRepository.deleteById(stIdx);
    }
}
