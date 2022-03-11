package com.koreait.cgvproject.service.admin.seat;

import com.koreait.cgvproject.dto.SeatDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Seat;
import com.koreait.cgvproject.repository.HallRepository;
import com.koreait.cgvproject.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminSeatService {
    private SeatRepository seatRepository;
    private HallRepository hallRepository;

    public boolean create(SeatDTO seatDTO) {
        Optional<Hall> hall = hallRepository.findById(seatDTO.getHcode());
        Seat seat = new Seat();
        if (hall.isPresent())
            seat = seatRepository.save(seatDTO.toEntityForCreateHallRequired(hall.get()));
        if (seat.getStcode() != null) return true;
        else return false;
    }

    public void update(SeatDTO seatDTO) {
        Optional<Hall> hall = hallRepository.findById(seatDTO.getHcode());
        if (hall.isPresent()) {
            Optional<Seat> seatOptional = seatRepository.findByHallAndStNum(hall.get(), seatDTO.getStNum());
            if (seatOptional.isPresent()) {
                long seatId = seatOptional.get().getStcode();
                Optional<Seat> seat = seatRepository.findById(seatId);
                seat.ifPresent(st -> {
                    st.setDisabled(seatDTO.getDisabled());
                });
            } else {
                // 상영관 코드와 좌석 이름에 맞지 않는 좌석;; 없는 좌석이 있을 경우 새로운 좌석을 만든다.
                seatRepository.save(seatDTO.toEntityForCreateHallRequired(hall.get()));
            }
        }
    }

    public List<SeatDTO> read(Long hcode) {
        Optional<Hall> hall = hallRepository.findById(hcode);
        List<SeatDTO> seatDTOList = new ArrayList<>();
        hall.ifPresent(hal -> {
            List<Seat> seatList = seatRepository.findAllByHallOrderByStNum(hal);
            seatList.forEach(seatEntity -> seatDTOList.add(seatEntity.toDTO()) );
        });
        return seatDTOList;
    }

    @Transactional
    public void delete(Long hcode){
        Optional<Hall> hall = hallRepository.findById(hcode);
        hall.ifPresent(hal -> { seatRepository.deleteAllByHall(hal);
        });
    }
}
