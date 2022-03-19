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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserScheduleService {

    private ScheduleRepository scheduleRepository;
    private MovieRepository movieRepository;
    private HallRepository hallRepository;
    private TheaterRepository theaterRepository;


    // 영화, 극장코드로 스케쥴 받아오기
    public List<ScheduleDTO> findScheduleList(Long mcode, Long tcode) {
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        Optional<Movie> movieOptional = movieRepository.findById(mcode);
        Optional<Theater> theaterOptional = theaterRepository.findById(tcode);
        if (movieOptional.isEmpty() || theaterOptional.isEmpty()) {
            System.out.println("에러 : 데이터가 존재하지 않습니다 : UserScheduleService : 45번쨰줄");
            return null;
        }
        List<Hall> hallList = hallRepository.findAllByTheater(theaterOptional.get());
        for (Hall hall : hallList) {
            List<Schedule> scheduleList = scheduleRepository.findAllByMovieAndHall(movieOptional.get(), hall);
            for (Schedule schedule : scheduleList) {
                ScheduleDTO scheduleDTO = schedule.toDTO();
                scheduleDTO.setMovieDTO(movieOptional.get().toDTO());
                scheduleDTOList.add(scheduleDTO);

            }
        }
        if(scheduleDTOList.isEmpty()){
            System.out.println("에러 : 리스트가 존재하지 않습니다");
            return null;
        }
        return scheduleDTOList;
    }

    //mcode랑 scdate가 들어있음
    public List<ScheduleDTO> findAllOnlyMcode(Long mcode, String scdate){

        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        Movie movie = outOptional(movieRepository.findById(mcode));

        List<Schedule> scheduleList= scheduleRepository.findAllByMovie(movie);
        if(!scheduleList.isEmpty()) {
            scheduleList.forEach(schedule -> {
                ScheduleDTO scheduleDTO = schedule.toDTO();
                assert movie != null; // 무비는 null이 아니라고 가정한다.
                scheduleDTO.setMovieDTO(movie.toDTO());
                scheduleDTOList.add(schedule.toDTO());
            });
            return scheduleDTOList;
        }
        return null;
    }

    //Optional 객체에서 알맹이만 빼내는 메서드
    private <T> T outOptional(Optional<T> optional){
        if(optional.isPresent()) return optional.get();
        else {
            System.out.println("비어있는 데이터 참조입니다 userScheduleService :: 63");
            return null;
        }
    }
}
