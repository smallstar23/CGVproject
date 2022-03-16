package com.koreait.cgvproject.service.user.schedule;

import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Schedule;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.HallRepository;
import com.koreait.cgvproject.repository.MovieRepository;
import com.koreait.cgvproject.repository.ScheduleRepository;
import com.koreait.cgvproject.repository.TheaterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private TheaterRepository theaterRepository;


    // 영화, 극장코드로 스케쥴 받아오기
    public List<ScheduleDTO> findScheduleList(Long mcode, Long tcode){
        List<ScheduleDTO> scheduleDTOList=new ArrayList<>();
        Movie movie=movieRepository.findByMcode(mcode);
        Theater theater=theaterRepository.findByTcode(tcode);
        List<Hall> hallList=hallRepository.findAllByTheater(theater);
        ScheduleDTO scheduleDTO=new ScheduleDTO();
        List<Schedule> scheduleList=new ArrayList<>();
            for(Hall hall: hallList){
                scheduleList=scheduleRepository.findAllByMovieAndHall(movie, hall);
                for(Schedule schedule: scheduleList){
                    scheduleDTO.setHallDTO(hall.toDTO());
                    scheduleDTO.setSchecode(schedule.getSchecode());
                    scheduleDTO.setScdate(schedule.getScdate());
                    scheduleDTOList.add(scheduleDTO);
            }
        }
        return scheduleDTOList;

    }
}
