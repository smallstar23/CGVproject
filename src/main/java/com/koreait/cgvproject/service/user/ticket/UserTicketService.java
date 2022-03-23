package com.koreait.cgvproject.service.user.ticket;

import com.koreait.cgvproject.dto.PriceDTO;
import com.koreait.cgvproject.dto.TicketDTO;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.entity.Price;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.entity.Ticket;
import com.koreait.cgvproject.repository.MovieRepository;
import com.koreait.cgvproject.repository.PriceRepository;
import com.koreait.cgvproject.repository.TheaterRepository;
import com.koreait.cgvproject.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserTicketService {
    private PriceRepository priceRepository;
    private TheaterRepository theaterRepository;

    @Autowired
    private TicketRepository ticketRepository;


    public PriceDTO getPrice(Long tcode, String week, String startTime){
        System.out.println("실행은 됨?");

        // 데이터베이스로 보낼 week로 가공
        final String[] HOLIDAYS = {"금","토","일"};
        final LocalTime morningTime = LocalTime.of(6,0,0); final LocalTime lunchTime = LocalTime.of(10,0,0); final LocalTime normalTime = LocalTime.of(13,0,0);
        String weekday = "월~목";

        for(String holiday : HOLIDAYS)
            if(week.equals(holiday)){
                weekday = "금~일";
                break;
            }

        // 데이터베이스로 보낼 시간 가공
        String[] temp = startTime.split(":");
        String time = "";LocalTime startTm = LocalTime.of(toInt(temp[0]),toInt(temp[1]));
        if(startTm.isBefore(lunchTime)) time = "06:00";
        else if(startTm.isBefore(normalTime)) time = "10:01";
        else time = "13:01";

        //theater 생성
        Theater theater = outOptional(theaterRepository.findById(tcode));
        Optional<Price> priceOptional = priceRepository.findByTheaterAndWeekAndStartTime(theater, weekday, time);
        Price price = outOptional(priceOptional);

        return price.toDTO();
    }


    private <T> T outOptional(Optional<T> optional){
        if(optional.isPresent())
            return optional.get();
        else{
            System.out.println("데이터가 없음");
            return null;
        }
    }

    private Integer toInt(String subject){
        try{
            Integer.parseInt(subject);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        return Integer.parseInt(subject);
    }

    // mycgv로 내보낼 ticketlist, 취소날짜가 있는경우 제외할 것
    public List<TicketDTO> mycgvTicket(Member member){
        List<TicketDTO> ticketDTOList=new ArrayList<>();
        List<Ticket> ticketList=ticketRepository.findAllByMember(member);
        for(Ticket ticket: ticketList){
            if(ticket.getCandate()==null){
                ticketDTOList.add(ticket.toDTO());
            }
        }
        return ticketDTOList;
    }

    //mycgv로 내보낼 것, 취소내역 리스트만
    public List<TicketDTO> mycgvCancelTicket(Member member){
        List<TicketDTO> ticketDTOList=new ArrayList<>();
        List<Ticket> ticketList=ticketRepository.findAllByMember(member);
        for(Ticket ticket: ticketList){
            if(ticket.getCandate()!=null){
                ticketDTOList.add(ticket.toDTO());
            }
        }
        System.out.println(ticketDTOList);
        return ticketDTOList;
    }
}
