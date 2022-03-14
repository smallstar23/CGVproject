package com.koreait.cgvproject.service.admin.schedule;

import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Schedule;
import com.koreait.cgvproject.repository.HallRepository;
import com.koreait.cgvproject.repository.MovieRepository;
import com.koreait.cgvproject.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private HallRepository hallRepository;

    public List<ScheduleDTO> getScheduleList(){
        List<ScheduleDTO> scheduleDTOList=new ArrayList<>();
        List<Movie> movieList=movieRepository.findAll();
        List<Hall> HallList=hallRepository.findAll();
        List<Schedule> scheduleList=new ArrayList<>();
        for(Movie movie:movieList){
            for(Hall hall:HallList){
                scheduleList=scheduleRepository.findAllByMovieAndHall(movie, hall);
                if(scheduleList!=null){
                    for(Schedule schedule: scheduleList){
                        ScheduleDTO scheduleDTO=new ScheduleDTO();
                        scheduleDTO.setSchecode(schedule.getSchecode());
                        scheduleDTO.setScdate(schedule.getScdate());
                        scheduleDTO.setMovieDTO(schedule.getMovie().toDTO());
                        scheduleDTO.setHallDTO(schedule.getHall().toDTO());
                        scheduleDTOList.add(scheduleDTO);
                }
                }

            }
        }
        return scheduleDTOList;
    }
}
