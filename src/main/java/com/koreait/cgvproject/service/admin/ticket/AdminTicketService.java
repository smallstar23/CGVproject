package com.koreait.cgvproject.service.admin.ticket;

import com.koreait.cgvproject.dto.TicketDTO;
import com.koreait.cgvproject.entity.Ticket;
import com.koreait.cgvproject.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminTicketService {

    @Autowired
    TicketRepository ticketRepository;

    // 전체 리스트 뽑기
    public List<TicketDTO> getAllTickets(){

        List<TicketDTO> ticketDTOList=new ArrayList<>();
        List<Ticket> ticketList=ticketRepository.findAll();
        if(ticketList!=null){
            for(Ticket ticket: ticketList){
                ticketDTOList.add(ticket.toDTO());
            }
        }
        return ticketDTOList;
    }

    // id값으로 티켓 찾아오기
    public TicketDTO getTicket(Long ticode){
        TicketDTO ticketDTO=new TicketDTO();
        Optional<Ticket> ticket=ticketRepository.findById(ticode);
        ticket.ifPresent(ticket1 -> {
            ticketDTO.setTicode(ticket1.getTicode());
            ticketDTO.setScheduleDTO(ticket1.getSchedule().toDTO());
            ticketDTO.setTotperson(ticket1.getTotperson());
            ticketDTO.setSeat(ticket1.getSeat());
            ticketDTO.setMemberDTO(ticket1.getMember().toDTO());
            ticketDTO.setPrice(ticket1.getPrice());
            ticketDTO.setPaydate(ticket1.getPaydate());
            ticketDTO.setTotprice(ticket1.getTotprice());

        });
        System.out.println(ticketDTO);
       return ticketDTO;

    }
    
    // 티켓 정보 삭제
    public void deleteTicket(Long ticode){
        ticketRepository.deleteById(ticode);
    }


}
