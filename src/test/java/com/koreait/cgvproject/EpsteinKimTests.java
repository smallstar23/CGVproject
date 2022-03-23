package com.koreait.cgvproject;

import com.koreait.cgvproject.dto.PriceDTO;
import com.koreait.cgvproject.entity.Price;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.PriceRepository;
import com.koreait.cgvproject.repository.TheaterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EpsteinKimTests extends CgVprojectApplicationTests{

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Test
    public void read(){
        // 요일을 걸러냄 파라미터로 들어온값 15:00 , 수요일
        final String[] HOLIDAYS = {"금","토","일"};
        boolean isHoliday = false;
        String week = "(금)"; // 파라미터
        String weekday = "월~목";
        Pattern pattern = Pattern.compile("[가-힣]");
        Matcher matcher = pattern.matcher(week);
        if(matcher.find())
            System.out.println(matcher.group());


        for(String holiday : HOLIDAYS){
            if (week.equals(holiday)) {
                weekday = "금~일";
                break;
            }
        }

        // 시간 설정
        String start = "23:40"; // 파라미터
        String[] temp = start.split(":");
        String time = "";
        LocalTime startTime = LocalTime.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), 0);
        final LocalTime morningTime = LocalTime.of(6,0,0); final LocalTime lunchTime = LocalTime.of(10,0,0); final LocalTime normalTime = LocalTime.of(13,0,0);

        if(startTime.isBefore(lunchTime)) time = "06:00";
        else if(startTime.isBefore(normalTime)) time = "10:01";
        else time = "13:01";
        System.out.println(time);
        System.out.println(weekday);
        Optional<Theater> theaterOptional = theaterRepository.findById(2L);
        Theater theater = null;
        if(theaterOptional.isPresent()) theater = theaterOptional.get();

        Optional<Price> priceOptional = priceRepository.findByTheaterAndWeekAndStartTime(theater, weekday, time);
        if(priceOptional.isPresent()){
            Price price = priceOptional.get();
            System.out.println(price.getAdultPrice());
            System.out.println(price.getStuPrice());
        }

    }
}
