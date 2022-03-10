package com.koreait.cgvproject.service.admin.seat;

import com.koreait.cgvproject.dto.HallDTO;
import com.koreait.cgvproject.dto.SeathtmlDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Seathtml;
import com.koreait.cgvproject.repository.HallRepository;
import com.koreait.cgvproject.repository.SeatHtmlRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminSeatService {

    private SeatHtmlRepository seatHtmlRepository;
    private HallRepository hallRepository;

    public boolean seatHtmlExist(Long hcode) {
        Optional<Hall> hall = hallRepository.findById(hcode);
        Optional<Seathtml> seatHtml = Optional.empty();
        if (hall.isPresent()) {
            seatHtml = seatHtmlRepository.findByHall(hall.get());
        }
        return seatHtml.isPresent();
    }

    public void seatHtmlCreate(SeathtmlDTO seathtmlDTO) {
        Optional<Hall> hall = hallRepository.findById(seathtmlDTO.getHcode());
        hall.ifPresent(ha -> seatHtmlRepository.save(seathtmlDTO.toEntityForCreate(ha)));
    }

    public void seatHtmlUpdate(SeathtmlDTO seathtmlDTO) {
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
        Optional<Hall> hall = hallRepository.findById(hcode);
        Optional<Seathtml> seathtml = Optional.empty();
        if (hall.isPresent()) seathtml = seatHtmlRepository.findByHall(hall.get());
        return seathtml.map(Seathtml::toDTO).orElse(new SeathtmlDTO());
//        if (seatHtml.isPresent()) return seatHtml.get().toDTO();
//        return new SeathtmlDTO();
    }

    public void seatHtmlDelete(Long stIdx) {
        seatHtmlRepository.deleteById(stIdx);
    }
}
