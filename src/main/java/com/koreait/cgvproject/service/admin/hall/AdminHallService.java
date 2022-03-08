package com.koreait.cgvproject.service.admin.hall;

import com.koreait.cgvproject.dto.HallDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.repository.HallRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminHallService {

    private HallRepository hallRepository;

    @Transactional
    public void savePost(HallDTO hallDTO){hallRepository.save(hallDTO.toEntity());}

    public List<HallDTO> getHallList(){
        List<Hall> hallList = hallRepository.findAll();
        List<HallDTO> hallDTOList = new ArrayList<>();

        for(Hall hall : hallList){
            HallDTO hallDTO = HallDTO.builder().hcode(hall.getHcode()).tcode(hall.getTcode()).location(hall.getLocation())
                    .hname(hall.getHname()).build();
            hallDTOList.add(hallDTO);
        }
        return hallDTOList;
    }
}
