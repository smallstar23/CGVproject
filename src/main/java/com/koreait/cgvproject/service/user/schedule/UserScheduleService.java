package com.koreait.cgvproject.service.user.schedule;

import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Schedule;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserScheduleService {

    private ScheduleRepository scheduleRepository;
    private MovieRepository movieRepository;
    private HallRepository hallRepository;
    private TheaterRepository theaterRepository;
    private SeatRepository seatRepository;


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
    //mcode, tcode, scdate가 들어있음
    public List<ScheduleDTO> findAllOnlyMcode(Long mcode, Long tcode, String scdate){

        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        Movie movie = outOptional(movieRepository.findById(mcode));

        List<Schedule> scheduleList= scheduleRepository.findAllByMovieOrderByScdate(movie);
        if(!scheduleList.isEmpty()) {
            for (Schedule schedule : scheduleList) {
                String scheduleScdate = null;
                ScheduleDTO scheduleDTO = schedule.toDTO();


                Pattern regex = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                Matcher regexMatcher = regex.matcher(scheduleDTO.getScdate().toString());
                if (regexMatcher.find()) scheduleScdate = regexMatcher.group();

                // 날짜가 일치하지 않으면 거름
                if (!scheduleScdate.equals(scdate)) continue;
                // 해당 CGV의 정보가 아니면 거름
                if(!scheduleDTO.getHallDTO().getTheater().getTcode().equals(tcode)) continue;
                // 해당 CGV정보는 스케줄을 부를때 필요 없으므로 null로 세팅 ( 개발자 도구에서 보기가 불편함 )
                scheduleDTO.getHallDTO().setTheater(null);

                assert movie != null; // 무비는 null이 아니라고 가정한다.
                scheduleDTO.setMovieDTO(movie.toDTO());
                scheduleDTOList.add(scheduleDTO);
            }
            return scheduleDTOList;
        }
        return null;
    }

    public Long getSeatCount(Long hcode){
        Hall hall = outOptional(hallRepository.findById(hcode));
        return seatRepository.countAllByHallAndDisabledEquals(hall, 0);
    }

    //Optional 객체에서 알맹이만 빼내는 메서드
    private <T> T outOptional(Optional<T> optional){
        if(optional.isPresent()) return optional.get();
        else {
            System.out.println("비어있는 데이터 참조입니다 userScheduleService :: Optional");
            return null;
        }
    }


    // 날짜, 영화관 정보로 스케쥴 받아오기
    public List<ScheduleDTO> findbyDate(Long tcode, LocalDateTime scdate){
        LocalDateTime enddate=scdate.plusDays(1);
        Theater theater=theaterRepository.findByTcode(tcode);
        List<Schedule> scheduleList=new ArrayList<>();
        List<ScheduleDTO> scheduleDTOList=new ArrayList<>();
        if(theater!=null){
            List<Hall> hallList=hallRepository.findAllByTheater(theater);
            if(hallList!=null){
                for(Hall hall: hallList) {
                    scheduleList=scheduleRepository.findAllByHallAndScdateBetween(hall, scdate, enddate);
                    for(Schedule schedule:scheduleList){
                        scheduleDTOList.add(schedule.toDTO());
                    }
                }
            }
        }

        return scheduleDTOList;
    }

}
