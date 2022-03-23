package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.MemberDTO;
import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.entity.Schedule;
import com.koreait.cgvproject.entity.Ticket;
import com.koreait.cgvproject.repository.MemberRepository;
import com.koreait.cgvproject.repository.ScheduleRepository;
import com.koreait.cgvproject.repository.TicketRepository;
import com.koreait.cgvproject.service.Kakaopay;
import com.koreait.cgvproject.service.admin.theater.AdminTheaterService;
import com.koreait.cgvproject.service.user.moive.UserMovieService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Log
@Controller
@AllArgsConstructor
@Slf4j
public class UserTicketController {

    private UserMovieService userMovieService;
    private AdminTheaterService adminTheaterService;
    private ScheduleRepository scheduleRepository;
    private MemberRepository memberRepository;
    private TicketRepository ticketRepository;
    private HttpSession session;

    @GetMapping("ticket")
    public String reservation(Model model){
        List<MovieDTO> movieDTOList=userMovieService.getList();
        List<TheaterDTO> theaterDTOList=adminTheaterService.findAll();
        model.addAttribute("movielist", movieDTOList);
        model.addAttribute("theaterlist",theaterDTOList);
        return "user/ticket/reservation";
    }


    @GetMapping("/user/ticket/home_ticket")
    public String homeTicket(){
        return "user/ticket/home_ticket";
    }


    @Setter(onMethod_ = @Autowired)
    private Kakaopay kakaopay;


    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestParam String movieName,
                           @RequestParam Long memIdx,
                           @RequestParam String seat,
                           @RequestParam Long schecode,
                           @RequestParam Long adultNum,
                           @RequestParam Long youthNum,
                           @RequestParam String price,
                           @RequestParam String totprice
                           ) {
        System.out.println(movieName);
        System.out.println(memIdx);
        System.out.println(seat);
        System.out.println(schecode);
        System.out.println(adultNum);
        System.out.println(youthNum);
        System.out.println(price);
        System.out.println(totprice);
        session.setAttribute("movieName",movieName);
        session.setAttribute("memIdx",memIdx);
        session.setAttribute("selSeat",seat);
        session.setAttribute("schecode",schecode);
        session.setAttribute("adultnum",adultNum);
        session.setAttribute("youthnum",youthNum);
        session.setAttribute("price",price);
        session.setAttribute("totprice",totprice);

        log.info("kakaoPay post............................................");
        return "redirect:" + kakaopay.kakaoPayReady(movieName);

    }

    @GetMapping("ticket/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpServletRequest request)throws  Exception {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        HttpSession session = request.getSession();
        Long memIdx = (Long) session.getAttribute("memIdx");
        String selSeat = (String) session.getAttribute("selSeat");
        Long schecode = (Long) session.getAttribute("schecode");
        Long adultnum = (Long) session.getAttribute("adultnum");
        Long youthnum = (Long) session.getAttribute("youthnum");
        String price = (String) session.getAttribute("price");
        String totprice = (String) session.getAttribute("totprice");

        Schedule schedule =scheduleRepository.findById(schecode).orElse(null);
        Member member =memberRepository.findByIdx(memIdx);

        Ticket ticket =new Ticket();
        ticket.setSchedule(schedule);
        ticket.setMember(member);
        ticket.setSeat(selSeat);
        ticket.setPrice(price);
        ticket.setTotprice(totprice);
        ticket.setPaydate(LocalDateTime.now());
        ticket.setAdultnum(adultnum);
        ticket.setYouthnum(youthnum);

        ticketRepository.save(ticket);

        model.addAttribute("ticketInfo",ticket);
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
        return "user/ticket/kakaoPaySuccess";

    }



//    private String totprice;


}
