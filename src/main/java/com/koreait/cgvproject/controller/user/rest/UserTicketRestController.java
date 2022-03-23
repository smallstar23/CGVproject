package com.koreait.cgvproject.controller.user.rest;

import com.koreait.cgvproject.dto.PriceDTO;
import com.koreait.cgvproject.dto.ScheduleDTO;
import com.koreait.cgvproject.dto.TicketDTO;
import com.koreait.cgvproject.service.admin.ticket.AdminTicketService;
import com.koreait.cgvproject.service.user.schedule.UserScheduleService;
import com.koreait.cgvproject.service.user.ticket.UserTicketService;
import lombok.AllArgsConstructor;
import org.apache.catalina.util.ToStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserTicketRestController {

    private UserScheduleService userScheduleService;
    private UserTicketService userTicketService;

    @Autowired
    private AdminTicketService adminTicketService;


    private HttpSession session;

    @PostMapping("/api/findSchedule")
    public List<ScheduleDTO> scheduleDTOList(@RequestBody ScheduleDTO scheduleDTO){
        return userScheduleService.findScheduleList(scheduleDTO.getMcode(), scheduleDTO.getTcode());
    }
    @GetMapping("/api/findSchedule")
    public List<ScheduleDTO> findAllOnlyMcode(@RequestParam Long mcode, @RequestParam String scdate,
                                              @RequestParam Long tcode){
        return userScheduleService.findAllOnlyMcode(mcode, tcode, scdate);
    }

    @GetMapping("/api/schedule/getSeatCount")
    public Long getSeatCount(@RequestParam Long hcode){
        return userScheduleService.getSeatCount(hcode);
    }

    @GetMapping("/api/ticket/getPrice")
    public PriceDTO getPrice(@RequestParam String tcode, @RequestParam String week, @RequestParam String startTime) {
        return userTicketService.getPrice(Long.parseLong(tcode), week, startTime);
    }

    @PostMapping("/api/receiveInfo")
    public void receiveInfo(@RequestBody TicketDTO ticketDTO){
        System.out.println(ticketDTO);
    }

    @GetMapping("/api/deleteSchedule/{ticode}")
    public void deleteSchedule(@PathVariable Long ticode){
        adminTicketService.deleteTicket(ticode);
    }
}
