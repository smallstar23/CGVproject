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

    private ScheduleRepository scheduleRepository;
    private MovieRepository movieRepository;
    private HallRepository hallRepository;

    // 스케쥴 전체 리스트 찾기
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
                        scheduleDTO.setEnddate(schedule.getScdate().plusMinutes(schedule.getMovie().getRuntime()));
                        scheduleDTOList.add(scheduleDTO);
                }
                }

            }
        }
        return scheduleDTOList;
    }
    // 스케쥴 insert
    public int addSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule=new Schedule();
        Movie movie=movieRepository.findByMcode(scheduleDTO.getMcode());
        Hall hall=hallRepository.findByHcode(scheduleDTO.getHcode());
        if(movie!=null || hall!=null){
            schedule.setSchecode(scheduleDTO.getSchecode());
            schedule.setScdate(scheduleDTO.getScdate());
            schedule.setHall(hall);
            schedule.setMovie(movie);
            scheduleRepository.save(schedule);

            return 1;
        }
        return 0;

    }

    // 스케쥴 코드로 해당하는 스케쥴 찾기
    public ScheduleDTO findSchedule(Long schecode){
        Optional<Schedule> schedule=scheduleRepository.findById(schecode);
        if(schedule.isPresent()){
            ScheduleDTO scheduleDTO=schedule.get().toDTO();
            Movie movie=movieRepository.findByMcode(scheduleDTO.getMcode());
            Hall hall=hallRepository.findByHcode(scheduleDTO.getHcode());
            scheduleDTO.setMovieDTO(movie.toDTO());
            scheduleDTO.setHallDTO(hall.toDTO());
            return scheduleDTO;
        }
        return null;
    }

    // 삭제
    public void deleteSchedule(Long schecode){
        Optional<Schedule> schedule=scheduleRepository.findById(schecode);
        schedule.ifPresent(schedule1 -> {
            scheduleRepository.delete(schedule1);
                    System.out.println("삭제되었습니다.");
        }
        );

    }

}
